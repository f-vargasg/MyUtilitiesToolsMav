package com.fvgprinc.tools.utilities.swing.ui;

import com.fvgprinc.tools.utilities.MySwingUtil;
import com.fvgprinc.tools.utilities.swing.model.ColumnGridBe;
import com.fvgprinc.tools.utilities.swing.model.PickListGenTableModel;
import com.fvgprinc.tools.utilities.swing.model.PickListTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Diálogo genérico de catálogo / PickList con búsqueda y filtrado.
 * Construido con Layouts estándar de Swing (BorderLayout y FlowLayout).
 * 
 * @author garfi
 * @param <T> Tipo de objeto/DTO contenido en la tabla
 */
public class JfrmPickListGen<T> extends JDialog {

    private int result = JOptionPane.CANCEL_OPTION;

    private final List<ColumnGridBe> lstGridColsBe;
    private PickListGenTableModel<T> dataModel;

    private Object dataPick;
    private Object descPick;
    private String columnDataPick;
    private String columnDescPick;
    private T selectedObject;

    // Componentes de la UI
    private JTextField jTxtFldToSearch;
    private JButton jBtnSearch;
    private JTable jTblData;
    private JButton jBtnOk;
    private JButton jBtnCancel;

    public JfrmPickListGen(Frame parent, boolean modal, String title, List<ColumnGridBe> lstCols) {
        super(parent, title, modal);
        this.lstGridColsBe = lstCols != null ? lstCols : new ArrayList<>();

        initUI();
        initEvents();

        this.setLocationRelativeTo(parent);
    }

    /**
     * Construcción manual de la interfaz gráfica usando Layout Managers estándar.
     */
    private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Panel Contenedor Principal (BorderLayout con márgenes de 10px)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Panel Superior: Búsqueda (FlowLayout alineado a la izquierda)
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlSearch.setBorder(BorderFactory.createEtchedBorder());
        
        JLabel lblSearch = new JLabel("Buscar:");
        jTxtFldToSearch = new JTextField(25);
        jBtnSearch = new JButton("Buscar");

        pnlSearch.add(lblSearch);
        pnlSearch.add(jTxtFldToSearch);
        pnlSearch.add(jBtnSearch);

        // 2. Panel Central: Tabla de Datos (BorderLayout)
        JPanel pnlGrid = new JPanel(new BorderLayout());
        pnlGrid.setBorder(BorderFactory.createEtchedBorder());

        jTblData = new JTable();
        jTblData.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTblData);
        
        pnlGrid.add(scrollPane, BorderLayout.CENTER);

        // 3. Panel Inferior: Botones de Acción (FlowLayout centrado)
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlButtons.setBorder(BorderFactory.createEtchedBorder());

        jBtnOk = new JButton("Ok");
        jBtnCancel = new JButton("Cancelar");
        
        // Dimensiones parejas para los botones
        Dimension btnSize = new Dimension(90, 28);
        jBtnOk.setPreferredSize(btnSize);
        jBtnCancel.setPreferredSize(btnSize);

        pnlButtons.add(jBtnOk);
        pnlButtons.add(jBtnCancel);

        // Ensamblar todo en el panel principal
        mainPanel.add(pnlSearch, BorderLayout.NORTH);
        mainPanel.add(pnlGrid, BorderLayout.CENTER);
        mainPanel.add(pnlButtons, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        
        // Tamaño por defecto del diálogo
        setSize(600, 500);
        setMinimumSize(new Dimension(450, 350));
    }

    /**
     * Asignación de eventos mediante Lambdas y Listeners de Swing.
     */
    private void initEvents() {
        // Eventos de búsqueda (Botón Buscar o Tecla ENTER en la caja de texto)
        jBtnSearch.addActionListener(e -> executeSearch());
        jTxtFldToSearch.addActionListener(e -> executeSearch());

        // Eventos de botones
        jBtnOk.addActionListener(e -> selectRowTableValue());
        jBtnCancel.addActionListener(e -> cancelSelection());

        // Doble clic en la tabla para seleccionar
        jTblData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectRowTableValue();
                }
            }
        });

        // Tecla ENTER en la tabla para seleccionar
        jTblData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // Evita saltar a la siguiente fila
                    selectRowTableValue();
                }
            }
        });
    }

    /**
     * Ejecuta el filtrado dinámico en el TableRowSorter.
     */
    private void executeSearch() {
        if (jTblData.getRowSorter() != null) {
            @SuppressWarnings("unchecked")
            TableRowSorter<PickListGenTableModel<T>> sorter = (TableRowSorter<PickListGenTableModel<T>>) jTblData.getRowSorter();
            String text = jTxtFldToSearch.getText().trim();
            if (text.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
            }
        }
    }

    /**
     * Procesa la fila seleccionada y recupera sus valores/objeto.
     */
    private void selectRowTableValue() {
        int viewRow = jTblData.getSelectedRow();
        if (viewRow != -1) {
            // Mapear el índice visual al índice real del modelo (Soporta filtros y ordenamientos)
            int modelRow = jTblData.convertRowIndexToModel(viewRow);

            int dataCol = getColumnIndexByName(this.columnDataPick);
            int descCol = getColumnIndexByName(this.columnDescPick);

            this.dataPick = (dataCol != -1) ? jTblData.getModel().getValueAt(modelRow, dataCol) : null;
            this.descPick = (descCol != -1) ? jTblData.getModel().getValueAt(modelRow, descCol) : null;

            if (dataModel != null) {
                this.selectedObject = dataModel.getRowObject(modelRow);
            }

            this.result = JOptionPane.OK_OPTION;
            this.dispose();
        } else {
            MySwingUtil.mostrarMensaje("No seleccionó ningún registro", MySwingUtil.TD_ERROR, "Edición");
        }
    }

    private void cancelSelection() {
        this.result = JOptionPane.CANCEL_OPTION;
        this.dispose();
    }

    private int getColumnIndexByName(String columnName) {
        if (columnName == null) return -1;
        for (int i = 0; i < lstGridColsBe.size(); i++) {
            ColumnGridBe gridBe = lstGridColsBe.get(i);
            if (gridBe.getNomFisico().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    // --- Métodos de Configuración y Getters ---

    public void setDataModel(PickListGenTableModel<T> pickListTableModel) {
        this.dataModel = pickListTableModel;
        jTblData.setModel(pickListTableModel);
        
        TableRowSorter<PickListGenTableModel<T>> sorter = new TableRowSorter<>(pickListTableModel);
        jTblData.setRowSorter(sorter);
    }

    public void setColumnTableDataWidth(int col, int width) {
        if (col >= 0 && col < jTblData.getColumnCount()) {
            this.jTblData.getColumnModel().getColumn(col).setPreferredWidth(width);
        }
    }

    public int showDialog() {
        setVisible(true);
        return result;
    }

    public Object getDataPick() { return dataPick; }
    public void setDataPick(Object dataPick) { this.dataPick = dataPick; }

    public Object getDescPick() { return descPick; }
    public void setDescPick(Object descPick) { this.descPick = descPick; }

    public String getColumnDataPick() { return columnDataPick; }
    public void setColumnDataPick(String columnDataPick) { this.columnDataPick = columnDataPick; }

    public String getColumnDescPick() { return columnDescPick; }
    public void setColumnDescPick(String columnDescPick) { this.columnDescPick = columnDescPick; }

    public T getSelectedObject() { return selectedObject; }
    public JTable getjTblData() { return jTblData; }
}