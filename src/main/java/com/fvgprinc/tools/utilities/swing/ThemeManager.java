
package com.fvgprinc.tools.utilities.swing;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Component;

public class ThemeManager {

    /**
     * Establece el Look and Feel del sistema basándose en un nombre descriptivo.
     * @param skinName El nombre del skin (ej: "Nimbus", "Windows", "Metal", "CDE/Motif")
     * @param root El componente principal (Frame) para refrescar la UI, puede ser null.
     */
    public static void setSkin(String skinName, Component root) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                // Buscamos coincidencia ignorando mayúsculas/minúsculas
                if (info.getName().equalsIgnoreCase(skinName)) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            // Si pasaste un componente (como el JFrame principal), refrescamos toda la jerarquía
            if (root != null) {
                SwingUtilities.updateComponentTreeUI(root);
            }
            
        } catch (Exception e) {
            System.err.println("No se pudo aplicar el skin: " + skinName);
            try {
                // Fallback al sistema nativo si el solicitado falla
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}