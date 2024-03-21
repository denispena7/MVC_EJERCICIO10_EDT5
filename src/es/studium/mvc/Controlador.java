package es.studium.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class Controlador implements WindowListener, ActionListener
{
	Modelo m;
	Vista v;
	
	public Controlador(Modelo modelo, Vista vista) 
	{
		m = modelo;
		v = vista;
		
		vista.proximo.addActionListener(this);
		vista.anterior.addActionListener(this);
		vista.primero.addActionListener(this);
		vista.ultimo.addActionListener(this);
		vista.ventana.addWindowListener(this);
		
		try
		{
			v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
			v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
	}
	
	public void windowActivated(WindowEvent windowEvent)
	{
	}
	public void windowClosed(WindowEvent windowEvent)
	{
	}
	public void windowClosing(WindowEvent windowEvent)
	{
		// Cerrar los elementos de la base de datos
		try
		{
			m.rs.close();
			m.stmt.close();
			m.con.close();
		} catch (SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent windowEvent)
	{
	}
	public void windowDeiconified(WindowEvent windowEvent)
	{
	}
	public void windowIconified(WindowEvent windowEvent)
	{
	}
	public void windowOpened(WindowEvent windowEvent)
	{
	}
	public void actionPerformed(ActionEvent actionEvent)
	{
		// Hemos pulsado Próximo
		if (v.proximo.equals(actionEvent.getSource()))
		{
			try
			{
				// Si no hemos llegado al final
				if (m.rs.next())
				{
					// Poner en los TextField los valores obtenidos
					v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
					v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
				} 
				else
				{
					// Mueve el cursos al registro anterior
					m.rs.previous();
					v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
					v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
				}
			} 
			catch (SQLException e)
			{
				System.out.println("Error en la sentencia SQL" +
						e.getMessage());
			}
		}
		// Hemos pulsado Previo
		if (v.anterior.equals(actionEvent.getSource()))
		{
			try
			{
				// Si no hemos llegado al final
				if (m.rs.previous())
				{
					// Poner en los TextField los valores obtenidos
					v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
					v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
				} else
				{
					m.rs.next();
					v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
					v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
				}
			} catch (SQLException e)
			{
				System.out.println("Error en la sentencia SQL" +
						e.getMessage());
			}
		}
		if (v.primero.equals(actionEvent.getSource()))
		{
			try
			{
				m.rs.first();
				v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
				v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
			} catch (SQLException e)
			{
				System.out.println("Error en la sentencia SQL" +
						e.getMessage());
			}
		}
		if (v.ultimo.equals(actionEvent.getSource()))
		{
			try
			{
				m.rs.last();
				v.idEmpleado.setText(Integer.toString(m.rs.getInt("idEmpleado")));
				v.nombreEmpleado.setText(m.rs.getString("nombreEmpleado"));
			} catch (SQLException e)
			{
				System.out.println("Error en la sentencia SQL" +
						e.getMessage());
			}
		}
	}
}