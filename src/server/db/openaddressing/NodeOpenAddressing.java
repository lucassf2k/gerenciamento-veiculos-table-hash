package server.db.openaddressing;

import server.entities.Vehicle;

public class NodeOpenAddressing {
  private long value;
  private Vehicle vehicle;

  public NodeOpenAddressing() {}  

  public NodeOpenAddressing(Vehicle vehicle) {
    this.value = vehicle.getRenavam();
    this.vehicle = vehicle;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  

}
