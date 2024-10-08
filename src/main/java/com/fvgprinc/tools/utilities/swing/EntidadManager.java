package com.fvgprinc.tools.utilities.swing;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
/*
    Ok, no todo es color de rosa, que tengo que hacer 
*/
public class EntidadManager {

        // Método para poblar los valores de la entidad a los componentes
    public static <T> void mapEntityToComponents(T entity, Map<String, JComponent> components) throws Exception {
        Class<?> clazz = entity.getClass();

        for (Map.Entry<String, JComponent> entry : components.entrySet()) {
            String fieldName = entry.getKey();
            JComponent component = entry.getValue();

            // Convertir el nombre del campo en el nombre del método getter
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            // Obtener el método del getter
            Method getterMethod = clazz.getMethod(getterName);

            // Invocar el getter para obtener el valor
            Object value = getterMethod.invoke(entity);

            // Asignar el valor al componente correspondiente
            setValueToComponent(component, value);
        }
    }

    // Método para asignar el valor de un componente a la entidad
    public static <T> void mapComponentsToEntity(Map<String, JComponent> components, T entity) throws Exception {
        Class<?> clazz = entity.getClass();

        for (Map.Entry<String, JComponent> entry : components.entrySet()) {
            String fieldName = entry.getKey();
            JComponent component = entry.getValue();

            // Convertir el nombre del campo en el nombre del método setter
            String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            // Obtener el tipo del campo (puede variar dependiendo de la entidad)
            Method getterMethod = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            Class<?> fieldType = getterMethod.getReturnType();

            // Obtener el valor del componente
            Object value = getValueFromComponent(component, fieldType);

            // Buscar el setter correspondiente
            Method setterMethod = clazz.getMethod(setterName, fieldType);

            // Invocar el setter para asignar el valor
            setterMethod.invoke(entity, value);
        }
    }

    // Método para asignar el valor a un componente Swing
    
    private static void setValueToComponent(JComponent component, Object value) {
        if (component instanceof JTextField && value instanceof String) {
            ((JTextField) component).setText((String) value);
        } else if (component instanceof JComboBox && value != null) {
            JComboBox<Object> comboBox = (JComboBox<Object>) component;

            // Verificar si tiene un atributo personalizado para buscar
            String fieldName = (String) component.getClientProperty("fieldName");
            if (fieldName != null) {
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    Object item = comboBox.getItemAt(i);
                    if (doesObjectMatchField(item, fieldName, value)) {
                        comboBox.setSelectedIndex(i);
                        return;
                    }
                }
            } else {
                comboBox.setSelectedItem(value);
            }
        } else if (component instanceof JList && value != null) {
            JList<Object> list = (JList<Object>) component;

            // Verificar si tiene un atributo personalizado para buscar
            String fieldName = (String) component.getClientProperty("fieldName");
            if (fieldName != null) {
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    Object item = list.getModel().getElementAt(i);
                    if (doesObjectMatchField(item, fieldName, value)) {
                        list.setSelectedIndex(i);
                        return;
                    }
                }
            } else {
                list.setSelectedValue(value, true);
            }
        }
    }
    
    private static boolean doesObjectMatchField(Object item, String fieldName, Object value) {
        try {
            if (item == null || value == null) {
                return false;
            }
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object fieldValue = field.get(item);
            return value.equals(fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener el valor desde un componente y convertirlo al tipo requerido
    private static Object getValueFromComponent(JComponent component, Class<?> fieldType) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (component instanceof JTextField) {
            String text = ((JTextField) component).getText();

            if (fieldType == String.class) {
                return text;
            } else if (fieldType == int.class || fieldType == Integer.class) {
                return Integer.parseInt(text);
            } else if (fieldType == double.class || fieldType == Double.class) {
                return Double.parseDouble(text);
            } else if (fieldType == float.class || fieldType == Float.class) {
                return Float.parseFloat(text);
            }
            // Manejar más conversiones según sea necesario
        } else if (component instanceof JComboBox) {
           Object selectedItem = ((JComboBox<?>) component).getSelectedItem();

            // Verificar si hay una propiedad personalizada para seleccionar un atributo del objeto
            String fieldName = (String) component.getClientProperty("fieldName");
            if (fieldName != null && selectedItem != null) {
                Field field = selectedItem.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(selectedItem);  // Retornar el atributo específico del objeto seleccionado
            }

            return selectedItem;
        } else if (component instanceof JList) {
            return ((JList<?>) component).getSelectedValue();
        }

        return null; // Valor predeterminado si no se reconoce el componente
    }
}
