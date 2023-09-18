package server.types;

public interface TableHash<T, E> {
  public void insert(T vehicleT);
  public E search(long k);
  public E searchByLicensePlate(String licensePlate);
  public void update(long k, T vehicle);
  public void delete(long renavam);
  public void show();
}
