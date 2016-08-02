package spike.drools.model;

public class Item {

  private String descripcion;
  private double valorUnitario;
  private long cantidad;
  
  public String getDescripcion() {
    return descripcion;
  }
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  public double getValorUnitario() {
    return valorUnitario;
  }
  public void setValorUnitario(double valorUnitario) {
    this.valorUnitario = valorUnitario;
  }
  public long getCantidad() {
    return cantidad;
  }
  public void setCantidad(long cantidad) {
    this.cantidad = cantidad;
  }
}
