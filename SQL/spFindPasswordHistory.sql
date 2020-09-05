DELIMITER $$

DROP PROCEDURE IF EXISTS spFindPasswordHistory $$

CREATE PROCEDURE spFindPasswordHistory (
	IN bannerID VARCHAR(20),
	IN password VARCHAR(76),
	IN history BIGINT
)

BEGIN
	SELECT id
	INTO @userID
	FROM User
	WHERE User.bannerID = bannerID; 

	SELECT p.userID, p.password, p.addedDateTime
	FROM (SELECT PasswordHistory.userID, PasswordHistory.password, PasswordHistory.addedDateTime
			FROM PasswordHistory
			WHERE PasswordHistory.userID = @userID
            ORDER BY addedDateTime DESC Limit history) AS p
	WHERE p.password = password;
END $$

DELIMITER ;