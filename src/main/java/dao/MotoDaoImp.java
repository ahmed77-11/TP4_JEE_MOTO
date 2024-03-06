package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metier.SingletonConnection;
import metier.entities.Moto;

public class MotoDaoImp implements IMotoDao {

	@Override
	public Moto save(Moto m) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO MOTOS(NOM_MOTO,PRIX) VALUES(?,?)");
			ps.setString(1, m.getNomMoto());
			ps.setDouble(2, m.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_MOTO) as MAX_ID FROM MOTOS");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				m.setIdMoto(rs.getLong("MAX_ID"));
				;
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<Moto> motosParMC(String mc) {
		List<Moto> prods = new ArrayList<Moto>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from motos where NOM_MOTO LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Moto p = new Moto();
				p.setIdMoto(rs.getLong("ID_MOTO"));
				p.setNomMoto(rs.getString("NOM_MOTO"));
				p.setPrix(rs.getDouble("PRIX"));
				prods.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Moto getMoto(Long id) {
		Connection conn = SingletonConnection.getConnection();
		Moto p = new Moto();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from MOTOS where ID_MOTO = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p.setIdMoto(rs.getLong("ID_MOTO"));
				p.setNomMoto(rs.getString("NOM_MOTO"));
				p.setPrix(rs.getDouble("PRIX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Moto updateMoto(Moto m) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE MOTOS SET NOM_MOTO=?,PRIX=? WHERE ID_MOTO=?");
			ps.setString(1, m.getNomMoto());
			ps.setDouble(2, m.getPrix());
			ps.setLong(3, m.getIdMoto());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void deleteMoto(Long id) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM MOTOS WHERE ID_MOTO = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
