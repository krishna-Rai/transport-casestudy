--create the stored procedure for fetching bus details (schedules) for a route
CREATE OR REPLACE FUNCTION get_route_schedules(route_id bigint)
RETURNS TABLE (
    start_time timestamp,
    end_time timestamp,
    bus_registration_no varchar,
    route_name varchar
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        s.departure_time,
        s.arrival_time,
        b.bus_number AS bus_registration_no,
        r.route_name
    FROM
        schedules s
    JOIN
        buses b ON s.bus_id = b.id
    JOIN
        routes r ON s.route_id = r.id
    WHERE
        s.route_id = get_route_schedules.route_id;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM get_route_schedules(1);
