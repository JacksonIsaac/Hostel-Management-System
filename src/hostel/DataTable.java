package hostel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * DataTable JTable used to display a SQL ResultSet.
 *
 * @author Jackson Isaac
 *
 */
public class DataTable extends JTable
{

	public DataTable(ResultSet rs)
	{
		DefaultTableModel model;

		model = new DefaultTableModel();
		setModel(model);

		setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
		setSize(this.getWidth(), this.getHeight());

		setBackground(Color.ORANGE);

		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		try {
			ResultSetMetaData rsMeta = rs.getMetaData();

			int colCount = rsMeta.getColumnCount();

			String[] colNames = new String[colCount];
			for (int i = 0; i < colCount; i++) {
				colNames[i] = rsMeta.getColumnName(i + 1);
			}
			model.setColumnIdentifiers(colNames);
			model.addRow(colNames);

			while (rs.next()) {
				String[] rowData = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					rowData[i] = rs.getString(i + 1);
				}
				model.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		tableResize();

	}

	public void tableResize()
	{
		for (int column = 0; column < this.getColumnCount(); column++) {
			TableColumn tableColumn = this.getColumnModel().getColumn(column);
			int preferredWidth = tableColumn.getMinWidth();
			int maxWidth = tableColumn.getMaxWidth();

			for (int row = 0; row < this.getRowCount(); row++) {
				TableCellRenderer cellRenderer = this.getCellRenderer(row, column);
				Component c = this.prepareRenderer(cellRenderer, row, column);
				int width = c.getPreferredSize().width + this.getIntercellSpacing().width + 10;
				preferredWidth = Math.max(preferredWidth, width);

				//  We've exceeded the maximum width, no need to check other rows
				if (preferredWidth >= maxWidth) {
					preferredWidth = maxWidth;
					break;
				}
			}

			tableColumn.setPreferredWidth(preferredWidth);
		}
		
		this.setRowHeight(20);
	}
}
