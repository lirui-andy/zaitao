set global event_scheduler=1;

DELIMITER &&

DROP EVENT IF EXISTS e_batch_compare &&
CREATE EVENT e_batch_compare
ON SCHEDULE EVERY 1 minute STARTS TIMESTAMP '2019-03-14 01:00:00'
ON COMPLETION PRESERVE
DO
BEGIN
CALL p_batch_compare();
END&&

DELIMITER ;
