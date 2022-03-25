package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbDAOImpl implements ReimbDAO {


    public List<Reimbursement> viewTicketsByAuthor(Integer reimb_author_id) {
        List<Reimbursement> reimbs = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where reimb_author_fk = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimb_author_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3),
                        rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbs;

    }

    @Override
    public Reimbursement viewTicketsById(Integer id) {
        Reimbursement reimbs = null;
        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs = (new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbs;
    }

    /*
    public List<Reimbursement> viewTicketsByResolver(Integer resolver_fk) {
        List<Reimbursement> reimbs = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance())
        {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_status_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where reimb_resolver_fk = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resolver_fk);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return reimbs;
    }*/

    @Override
    public void addReimbRequestSimple(Double reimb_amount, Integer reimb_author_fk, Integer reimb_type_id_fk) {
        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "insert into ers_reimbursement (reimb_amount, reimb_author_fk, reimb_type_id_fk) values (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, reimb_amount);
            ps.setInt(2, reimb_author_fk);
            ps.setInt(3, reimb_type_id_fk);

            ps.executeUpdate();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    @Override
    public void approveRequest(Integer resolver_fk, Integer id) {
        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "update ers_reimbursement set reimb_status_id_fk = 1, reimb_resolver_fk = ? where reimb_id =?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, resolver_fk);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void denyRequest(Integer resolver_fk, Integer id) {
        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "update ers_reimbursement set reimb_status_id_fk = 2, reimb_resolver_fk = ? where reimb_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resolver_fk);
            ps.setInt(2, id);

            ps.executeUpdate();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }


    @Override
    public List<Reimbursement> viewAllReimbAllEmployees() {
        List<Reimbursement> reimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbs;
    }

    @Override
    public List<Reimbursement> viewAllReimbNotSelf(Integer author_fk) {
        List<Reimbursement> reimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance();) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where not reimb_author_fk = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, author_fk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbs;
    }

    @Override
    public List<Reimbursement> filterByStatusNotSelf(Integer author_fk) {
        List<Reimbursement> reimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where not reimb_author_fk = ? order by reimb_status_id_fk;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, author_fk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbs;
    }

    @Override
    public List<Reimbursement> filterByIdNotSelf(Integer author_fk) {
        List<Reimbursement> reimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_type_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where not reimb_author_fk = ? order by reimb_id;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, author_fk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbs;
    }


   /*
    public List<Reimbursement>  pendingRequestsNotSelf(Integer author_fk)
    {
        List<Reimbursement> reimbs = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance()) {
            String sql = "select * from ers_reimbursement er inner join ers_reimbursement_type ert  on  er.reimb_status_id_fk = ert.reimb_type_id inner join ers_reimbursement_status ers  on  er.reimb_status_id_fk = ers.reimb_status_id where reimb_status_id_fk = 0 and reimb_author_fk != ? ;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,author_fk);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                reimbs.add(new Reimbursement(rs.getInt(1),rs.getDouble(2), rs.getTimestamp(3), rs.getInt(7), rs.getInt(8), rs.getString(12), rs.getString(14)));
            }
        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }


            return reimbs;
    }*/


    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */

}
