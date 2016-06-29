package com.maycon.hotelaria.tabelas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe que possui métodos responsáveis por manipular objetos JTable
 * @author Maycon
 */
public class TabelaFactory {

    /**
     * Método responsável por criar as tabelas que exibirão os dados cadastrados
     * no sistema
     *
     * @param cabecalho Object[] que representa o cabeçalho da tabela
     * @param dados Object[][] que representa os dados da tabela
     * @param tabela JTable que possui as propriedades da tabela
     * @return JTable que exibe os dados de interesse
     */
    public static JTable createTable(Object[] cabecalho, Object[][] dados, JTable tabela) {
        final DefaultTableModel tableModel = new DefaultTableModel(dados, cabecalho) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabela.setModel(tableModel);
        return tabela;
    }

    /**
     * Método responsável por inibir a primeira coluna de uma JTable
     *
     * @param tabela JTable que terá a primeira coluna inibida
     */
    public static void setTamanhoColuna(JTable tabela) {
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
    }

}
