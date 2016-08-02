package spike.drools.model;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ReglasPromocionTest {
  
  private KieSession ksession;
  
  
  @Before
  public void setUp() throws Exception {
    KieServices kieServices = KieServices.Factory.get();
    
    KieFileSystem kfs = kieServices.newKieFileSystem();
    KieResources kieResources = kieServices.getResources();
    // KieRepository kieRepository = kieServices.getRepository();
    
    Resource resource = kieResources.newClassPathResource("Promociones.drl");
    // path has to start with src/main/resources
    // append it with the package from the rule
    kfs.write("src/main/resources/spike/drools/rules/"+"Promociones.drl", resource);
    
    KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
    Results results = kieBuilder.getResults();
    
    if( results.hasMessages( Message.Level.ERROR ) ){
        System.out.println( results.getMessages() );
        throw new IllegalStateException( "### errors ###" );
    }
    
    KieContainer kieContainer = kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
    ksession = kieContainer.newKieSession();
  }
  
  
  @Test
  public void promocionTotalSuperiorA200() {
    Compra compraMayorA200 = compraMayorA200();
    
    ksession.insert(compraMayorA200);
    ksession.fireAllRules();
    
    Assert.assertEquals(compraMayorA200.getPorcentajeDescuento(), 15.0);
    
    //Solo es una impresion para saber el resultado por consola
    System.out.println("Descuento " + compraMayorA200.getPorcentajeDescuento() + "%");
  }
  
  
  // BUILDERS
  
  private Compra compraMayorA200() {
    List<Item> detalle = Arrays.asList(
        crearItem(2, 50000),
        crearItem(1, 100000),
        crearItem(1, 20000)
        );
    
    Compra compra = new Compra();
    compra.setDetalle(detalle);
    return compra;
  }

  private Item crearItem(int cantidad, int valor) {
    Item item = new Item();
    item.setCantidad(cantidad);
    item.setValorUnitario(valor);
    return item;
  }
}
