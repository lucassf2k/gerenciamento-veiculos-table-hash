package server.db.openaddressing;

import libs.FileController;
import server.db.outsideaddressing.NodeOutsideAddressing;
import server.entities.Vehicle;
import server.types.TableHash;

public class TableHashOpenAddressing implements TableHash<Vehicle, NodeOutsideAddressing> {
  private int M;
  private NodeOutsideAddressing table[];
  private int numberOfElementsAdded = 0;

  public TableHashOpenAddressing(int length) {
    this.M = length;
    this.table = new NodeOutsideAddressing[M];
  }

  private int hash(long ch, int k) {
    return (int)(ch % this.getM() + k * (1 + ch % (this.getM() - 1))) % this.getM();
  }

  public void insert(Vehicle v) {
    int attempts = 0;
    int h = this.hash(v.getRenavam(), attempts);
    while (this.table[h] != null) {
      if (this.table[h].getKey() == v.getRenavam()) break;
      h = this.hash(v.getRenavam(), ++attempts);
    }
    if (this.table[h] == null) {
      numberOfElementsAdded++;
      this.table[h] = new NodeOutsideAddressing();
      this.table[h].setKey(v.getRenavam());
      this.table[h].setVehicle(v);
      FileController.Write("./src/server/logs/logs.txt", "ADD: " + String.format("%.4f", this.getLoadFactor()));
    }
  }

  public NodeOutsideAddressing search(long v) {
    int attempts = 0;
    int h = this.hash(v, attempts);
    while (this.table[h] != null) {
      if (this.table[h].getKey() == v) return this.table[h];
      h = this.hash(v, ++attempts);
    }
    return null;
  }

  public NodeOutsideAddressing searchByLicensePlate(String licensePlate) {
    for (NodeOutsideAddressing node : this.table) {
      if (node != null && node.getVehicle().getLicencePlate().equals(licensePlate)) {
          return node;  // Retorna o veículo se a placa corresponder
      }
    }
    System.out.println("Veículo com a placa " + licensePlate + " não encontrado.");
    return null;
  }

  public void delete(long renavam) {
    int attempts = 0;
    int h = this.hash(renavam, attempts); 
    while (this.table[h] != null) {
        if (this.table[h].getKey() == renavam) {
          numberOfElementsAdded--;
            System.out.println("Veículo removido: " + this.table[h]);
            this.table[h] = null;  // Remove o veículo da posição h
            FileController.Write("./src/server/logs/logs.txt", "DEL: " + String.format("%.4f", this.getLoadFactor()));
            return;
        }
        h = this.hash(renavam, ++attempts);
    }
    System.out.println("Veículo com o renavam " + renavam + " não encontrado.");
  } 

  public void update(long renavam, Vehicle updatedVehicle) {
    int attempts = 0;
    int h = this.hash(renavam, attempts);
    while (this.table[h] != null) {
      if (this.table[h].getVehicle().getRenavam() == renavam) {
          this.table[h].setVehicle(updatedVehicle);
          return;
      }
      h = this.hash(renavam, ++attempts);
    }
    System.out.println("Veículo com o renavam " + renavam + " não encontrado.");
  }

  public void show() {
    for (int i = 0; i < this.getM(); i++) {
      if (this.table[i] != null) {
        System.out.println(i + " ---> " + this.table[i].toString());
      } else {
        System.out.println(i);
      }
    }
  }

  private float getLoadFactor() {
    float lenAdded = (float)this.getNumberOfElementsAdded();
    float lenTable = (float)this.getM();
    return lenAdded / lenTable;
  }

  public int getM() {
    return M;
  }
  public void setM(int m) {
    M = m;
  }
  public NodeOutsideAddressing[] getTable() {
    return table;
  }
  public void setTable(NodeOutsideAddressing[] table) {
    this.table = table;
  }

  public int getNumberOfElementsAdded() {
    return numberOfElementsAdded;
  }

  public void setNumberOfElementsAdded(int numberOfElementsAdded) {
    this.numberOfElementsAdded = numberOfElementsAdded;
  }

  
}
