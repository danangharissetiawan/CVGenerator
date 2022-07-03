package com.risset.dao;

import com.risset.entity.Personal;
import com.risset.util.DaoService;
import com.risset.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonalDaoImpl implements DaoService<Personal> {

    @Override
    public List<Personal> fechAll() throws SQLException, ClassNotFoundException {
        List<Personal> personals = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT id, full_name, email, phone, address, city, postal_code, website FROM personal";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Personal personal = new Personal();
                        personal.setId(rs.getInt("id"));
                        personal.setFullName(rs.getString("full_name"));
                        personal.setEmail(rs.getString("email"));
                        personal.setPhone(rs.getString("phone"));
                        personal.setAddress(rs.getString("address"));
                        personal.setCity(rs.getString("city"));
                        personal.setPostalCode(rs.getInt("postal_code"));
                        personal.setWebsite(rs.getString("website"));
                        personals.add(personal);
                    }
                }
            }
        }
        return personals;
    }

    @Override
    public int addData(Personal object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO personal VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getFullName());
                ps.setString(2, object.getEmail());
                ps.setString(3, object.getPhone());
                ps.setString(4, object.getAddress());
                ps.setString(5, object.getCity());
                ps.setInt(6, object.getPostalCode());
                ps.setString(7, object.getLinkedin());
                ps.setString(8, object.getGithub());
                ps.setString(9, object.getInstagram());
                ps.setString(10, object.getWebsite());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Personal object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE personal SET VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getFullName());
                ps.setString(2, object.getEmail());
                ps.setString(3, object.getPhone());
                ps.setString(4, object.getAddress());
                ps.setString(5, object.getCity());
                ps.setInt(6, object.getPostalCode());
                ps.setString(7, object.getLinkedin());
                ps.setString(8, object.getGithub());
                ps.setString(9, object.getInstagram());
                ps.setString(10, object.getWebsite());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Personal object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
