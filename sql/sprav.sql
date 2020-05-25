-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sprav
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sprav
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sprav` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `sprav` ;

-- -----------------------------------------------------
-- Table `sprav`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sprav`.`person` ;

CREATE TABLE IF NOT EXISTS `sprav`.`person` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 1016
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `sprav`.`contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sprav`.`contacts` ;

CREATE TABLE IF NOT EXISTS `sprav`.`contacts` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `telephone` VARCHAR(45) NOT NULL,
  `id_person` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `key_to_person_idx` (`id_person` ASC),
  CONSTRAINT `key_to_person`
    FOREIGN KEY (`id_person`)
    REFERENCES `sprav`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
