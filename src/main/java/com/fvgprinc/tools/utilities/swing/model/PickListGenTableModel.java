package com.fvgprinc.tools.utilities.swing.model;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Modelo de tabla genérico para Swing JTable enfocado en catalogos y PickLists.
 *
 * @author garfi
 * @param <T> Tipo de objeto DTO o Entidad contenida en la lista.
 */
public class PickListGenTableModel<T> extends AbstractTableModel {

    private List<T> data;
    private String[] columnNames;
    private String[] propertyNames; // Nombres físicos de las propiedades/atributos en el DTO

    // Caché de métodos Getter para optimizar el rendimiento del renderizado en la JTable
    private final Map<String, Method> getterCache = new HashMap<>();

    /**
     * Constructor básico recibiendo datos y nombres visibles de columnas.
     *
     * @param data Lista de objetos/DTOs a mostrar.
     * @param columnNames Encabezados visibles de la tabla.
     */
    public PickListGenTableModel(List<T> data, String[] columnNames) {
        this(data, columnNames, null);
    }

    /**
     * Constructor completo recibiendo datos, etiquetas de encabezados y nombres
     * físicos de los atributos.
     *
     * @param data Lista de objetos/DTOs a mostrar.
     * @param columnNames Encabezados visibles de la tabla.
     * @param propertyNames Nombres de las propiedades/atributos en la clase DTO
     * (ej: "idAccionMenu", "descripcion").
     */
    public PickListGenTableModel(List<T> data, String[] columnNames, String[] propertyNames) {
        this.data = (data != null) ? data : new ArrayList<>();
        this.columnNames = (columnNames != null) ? columnNames : new String[0];
        this.propertyNames = propertyNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < columnNames.length) {
            return columnNames[column];
        }
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()) {
            return null;
        }

        T rowObject = data.get(rowIndex);
        if (rowObject == null) {
            return null;
        }

        // Determinar la propiedad a leer para esta columna
        String propName = (propertyNames != null && columnIndex < propertyNames.length)
                ? propertyNames[columnIndex]
                : columnNames[columnIndex];

        return extractPropertyValue(rowObject, propName);
    }

    /**
     * Extrae el valor de un atributo usando reflexión (busca el método
     * getProperty() o isProperty()).
     */
    private Object extractPropertyValue(T object, String propertyName) {
        try {
            Class<?> clazz = object.getClass();
            String cacheKey = clazz.getName() + "." + propertyName;

            Method getter = getterCache.get(cacheKey);

            if (getter == null) {
                getter = findGetterMethod(clazz, propertyName);
                if (getter != null) {
                    getter.setAccessible(true);
                    getterCache.put(cacheKey, getter);
                }
            }

            if (getter != null) {
                return getter.invoke(object);
            }
        } catch (Exception e) {
            // Si ocurre algún fallo de reflexión, retorna null en silencio para no congelar la JTable
        }
        return null;
    }

    // =========================================================================
    // PEGA AQUÍ EL MÉTODO MEJORADO
    // =========================================================================
    private Method findGetterMethod(Class<?> clazz, String propertyName) {
        if (propertyName == null || propertyName.trim().isEmpty()) {
            return null;
        }

        String capitalized = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

        // 1. Probar getProperty() estándar (ej: getIdAccionMenu)
        try {
            return clazz.getMethod("get" + capitalized);
        } catch (NoSuchMethodException ignored) {
        }

        // 2. Probar getproperty() conservando la primera letra (ej: getfIdMenu)
        try {
            return clazz.getMethod("get" + propertyName);
        } catch (NoSuchMethodException ignored) {
        }

        // 3. Probar isProperty() para booleanos
        try {
            return clazz.getMethod("is" + capitalized);
        } catch (NoSuchMethodException ignored) {
        }
        try {
            return clazz.getMethod("is" + propertyName);
        } catch (NoSuchMethodException ignored) {
        }

        // 4. Búsqueda tolerante a mayúsculas/minúsculas
        for (Method method : clazz.getMethods()) {
            if (method.getParameterCount() == 0) {
                String methodName = method.getName();
                if (methodName.equalsIgnoreCase("get" + propertyName)
                        || methodName.equalsIgnoreCase("is" + propertyName)
                        || methodName.equalsIgnoreCase(propertyName)) {
                    return method;
                }
            }
        }

        return null;
    }

    /**
     * Método indispensable para recuperar el objeto DTO completo de una fila
     * dada. Utilizado internamente por JfrmPickList.
     *
     * @param rowIndex Índice de la fila en el modelo.
     * @return El objeto DTO de tipo T.
     */
    public T getRowObject(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }

    // --- Métodos de utilidad para actualizar datos ---
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = (data != null) ? data : new ArrayList<>();
        fireTableDataChanged();
    }
}
