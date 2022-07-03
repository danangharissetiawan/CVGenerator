package com.risset.dao;

import com.risset.entity.Education;
import com.risset.entity.Personal;
import com.risset.util.DaoService;
import com.risset.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl implements DaoService<Education> {


    @Override
    public List<Education> fechAll() throws SQLException, ClassNotFoundException {
        List<Education> educations = new ArrayList<>();
        try(Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT e.id, e.school, e.start_date, e.end_date, e.present, e.major, e.description, e.certificate, e.coursework, e.personal_id, f.full_name FROM education e INNER JOIN personal f ON e.personal_id = f.id";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Personal personal = new Personal();
                        personal.setId(rs.getInt("personal_id"));
                        personal.setFullName(rs.getString("full_name"));

                        Education education = new Education();
                        education.setId(rs.getInt("id"));
                        education.setSchoolName(rs.getString("school"));
                        education.setStartDate(rs.getDate("start_date"));
                        education.setEndDate(rs.getDate("end_date"));
                        education.setIsPresent(rs.getBoolean("present"));
                        education.setMajor(rs.getString("major"));
                        education.setDescription(rs.getString("description"));
                        education.setCertificate(rs.getString("certificate"));
                        education.setCoursework(rs.getString("coursework"));
                        education.setPersonal(personal);
                        educations.add(education);
                    }
                }
            }
        }
        return educations;
    }

    @Override
    public int addData(Education object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO education VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getSchoolName());
                ps.setDate(2, (Date) object.getStartDate());
                ps.setDate(3, (Date) object.getEndDate());
                ps.setBoolean(4, object.getIsPresent());
                ps.setString(5, object.getMajor());
                ps.setString(6, object.getDescription());
                ps.setString(7, object.getCertificate());
                ps.setString(8, object.getCoursework());
                ps.setInt(9, object.getPersonal().getId());

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
    public int updateData(Education object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE education SET id = ?, school = ?, start_date = ?, end_date = ?, present = ?, major = ?, description = ?, certificate = ?, coursework = ?, personal_id = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getId());
                ps.setString(2, object.getSchoolName());
                ps.setDate(3, (Date) object.getStartDate());
                ps.setDate(4, (Date) object.getEndDate());
                ps.setBoolean(5, object.getIsPresent());
                ps.setString(6, object.getMajor());
                ps.setString(7, object.getDescription());
                ps.setString(8, object.getCertificate());
                ps.setString(9, object.getCoursework());
                ps.setInt(10, object.getPersonal().getId());
                ps.setInt(11, object.getId());

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
    public int deleteData(Education object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM education WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getId());
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
}
