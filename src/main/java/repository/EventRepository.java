package repository;

import Models.DTO.createEventDTO;
import Models.DTO.updateEventDTO;
import Models.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventRepository extends BaseRepository<Event, createEventDTO, updateEventDTO> {

    public EventRepository() {
        super("Event");
    }

    @Override
    public Event fromResultSet(ResultSet res) {
        try {
            return Event.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Event create(createEventDTO dto) {
        String query = """
                INSERT INTO Event (event_name, organizer_name, event_date, event_time, room_id, description)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEvent_name());
            pstm.setString(2, dto.getOrganizer_name());
            pstm.setDate(3, new java.sql.Date(dto.getEvent_date().getTime()));
            pstm.setTime(4, Time.valueOf(dto.getEvent_time()));
            if (dto.getRoom_id() != null) {
                pstm.setInt(5, dto.getRoom_id());
            } else {
                pstm.setNull(5, Types.INTEGER);
            }
            pstm.setString(6, dto.getDescription());

            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Event update(updateEventDTO dto) {
        String query = """
                UPDATE Event
                SET event_name = ?, room_id = ?, description = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEvent_name());

            if (dto.getRoom_id() != null) {
                pstm.setInt(2, dto.getRoom_id());
            } else {
                pstm.setNull(2, Types.INTEGER);
            }

            pstm.setString(3, dto.getDescription());
            pstm.setInt(4, dto.getId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

