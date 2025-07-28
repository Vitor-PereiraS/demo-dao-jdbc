package model.dao.impl;

import db.DB;
import db.DbException;
import endities.Department;
import model.dao.DepartmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;


    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department " +
                            "(Name)" +
                            "VALUES " +
                            "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw  new DbException("Erro inesperado, nenhuma linha afetada");
            }
        }
        catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ? ");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

                st.setInt(1, id);

                int linha = st.executeUpdate();

                if (linha == 0){
                    throw new DbException("Não existe esse departamento");
                }
            }
            catch (SQLException e) {
                throw  new DbException(e.getMessage());
            }
            finally {
                DB.closeStatement(st);
            }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?;"
            );
            st.setInt(1, id);
                rs = st.executeQuery();
                if (rs.next()){
                    Department department = new Department();
                    department.setId(rs.getInt("Id"));
                    department.setName(rs.getString("Name"));
                    return department;
                }
                    return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department"
            );
            rs = st.executeQuery();
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                list.add(department);

            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
