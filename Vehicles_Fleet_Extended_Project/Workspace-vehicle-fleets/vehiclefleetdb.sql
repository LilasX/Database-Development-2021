
DROP USER vehiclefleetdb CASCADE;

CREATE USER vehiclefleetdb IDENTIFIED BY 123;

GRANT CONNECT, RESOURCE TO vehiclefleetdb;

GRANT ALL PRIVILEGES TO vehiclefleetdb;

CONNECT vehiclefleetdb/123;

SET lines 150
SET trimout on
SET tab off
Col dateofmanufacture format A20
Col made format A20
Col model format A20

CREATE TABLE GasVehicle(
serialNumber NUMBER(10), 
made VARCHAR2(20), 
model VARCHAR2(20), 
dateOfManufacture DATE, 
tripCounter NUMBER(10), 
fuelConsumed NUMBER(5,2)
);

CREATE UNIQUE INDEX IDX_GasVehicle_serialNumber on GasVehicle(serialNumber);

ALTER TABLE GasVehicle ADD CONSTRAINT PK_GasVehicle_serialNumber
PRIMARY KEY (serialNumber);

ALTER TABLE GasVehicle ADD CONSTRAINT CK_GasVehicle_serialNumber 
CHECK(serialNumber IS NOT NULL AND serialNumber > 0);

ALTER TABLE GasVehicle ADD CONSTRAINT CK_GasVehicle_tripCounter 
CHECK(tripCounter IS NOT NULL AND tripCounter > 0);

ALTER TABLE GasVehicle ADD CONSTRAINT CK_GasVehicle_fuelConsumed 
CHECK(fuelConsumed IS NOT NULL AND fuelConsumed > 0);

CREATE TABLE ElecVehicle(
serialNumber NUMBER(10), 
made VARCHAR2(20), 
model VARCHAR2(20), 
dateOfManufacture DATE, 
tripCounter NUMBER(10), 
kwPowerConsumed NUMBER(5,2)
);

CREATE UNIQUE INDEX IDX_ElecVehicle_serialNumber on ElecVehicle(serialNumber);

ALTER TABLE ElectricVehicle ADD CONSTRAINT PK_ElectricVehicle_serialNumber
PRIMARY KEY (serialNumber);

ALTER TABLE ElecVehicle ADD CONSTRAINT CK_ElecVehicle_serialNumber 
CHECK(serialNumber IS NOT NULL AND serialNumber > 0);

ALTER TABLE ElecVehicle ADD CONSTRAINT CK_ElecVehicle_tripCounter 
CHECK(tripCounter IS NOT NULL AND tripCounter > 0);

ALTER TABLE ElecVehicle ADD CONSTRAINT CK_ElecVehicle_kwPowerConsumed 
CHECK(kwPowerConsumed IS NOT NULL AND kwPowerConsumed > 0);
