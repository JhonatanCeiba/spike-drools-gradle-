package spike.drools.model;

import java.util.List;

public class Compra {

  private long id;
  private String cliente;
  private List<Item> detalle;
  private double porcentajeDescuento;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public List<Item> getDetalle() {
    return detalle;
  }

  public void setDetalle(List<Item> detalle) {
    this.detalle = detalle;
  }

  public double getPorcentajeDescuento() {
    return porcentajeDescuento;
  }

  public void setPorcentajeDescuento(double porcentajeDescuento) {
    this.porcentajeDescuento = porcentajeDescuento;
  }

  public double total() {
    return detalle.stream()
        .mapToDouble(i -> i.getValorUnitario() * i.getCantidad()).sum();
  }
}
