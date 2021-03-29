package database;

import static database.Constants.Tables.*;

public class SQLTableCreationFactory {
    public String getCreateSQLForTable(String table) {
        switch (table) {
            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS `role` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`));";

            case USER:
                return "CREATE TABLE IF NOT EXISTS `user` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `username` VARCHAR(45) NOT NULL,\n" +
                        "  `password` VARCHAR(100) NOT NULL,\n" +
                        "  `role_id` INT NOT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,\n" +
                        "  CONSTRAINT `role_id`\n" +
                        "    FOREIGN KEY (`role_id`)\n" +
                        "    REFERENCES `role` (`id`)\n" +
                        "    ON DELETE CASCADE\n" +
                        "    ON UPDATE CASCADE);";

            case CLIENT:
                return  "  CREATE TABLE IF NOT EXISTS `client` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `surname` VARCHAR(45) NOT NULL,\n" +
                        "  `identityCardNumber` VARCHAR(45) NOT NULL,\n" +
                        "  `cnp` VARCHAR(13) NOT NULL,\n" +
                        "  `address` VARCHAR(500) NOT NULL,\n" +
                        "  `mail` VARCHAR(100) NOT NULL,\n" +
                        "  `phone` VARCHAR(10) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`));";

            case ACCOUNT:
                return "   CREATE TABLE IF NOT EXISTS `account` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `identificationNumber` VARCHAR(45) NOT NULL,\n" +
                        "  `type` VARCHAR(45) NOT NULL,\n" +
                        "  `amountOfMoney` DOUBLE NOT NULL,\n" +
                        "  `creationDate` DATETIME NOT NULL,\n" +
                        "  `client_id` INT NOT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  INDEX `client_id_idx` (`client_id` ASC) VISIBLE,\n" +
                        "  CONSTRAINT `client_id`\n" +
                        "    FOREIGN KEY (`client_id`)\n" +
                        "    REFERENCES `client` (`id`)\n" +
                        "    ON DELETE CASCADE\n" +
                        "    ON UPDATE CASCADE);";

            case ACTIVITY:
                return "CREATE TABLE `activity` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `employeeID` INT NOT NULL,\n" +
                    "  `date` DATETIME NOT NULL,\n" +
                    "  `activity` VARCHAR(100) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `employeeID_idx` (`employeeID` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `employeeID`\n" +
                    "    FOREIGN KEY (`employeeID`)\n" +
                    "    REFERENCES `assignment1_test`.`user` (`id`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE);";



            default:
                return "";

        }
    }

}
