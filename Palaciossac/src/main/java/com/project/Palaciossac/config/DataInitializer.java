package com.project.Palaciossac.config;

import com.project.Palaciossac.customer.application.port.in.CreateCustomerCommand;
import com.project.Palaciossac.customer.application.port.in.CreateCustomerUseCase;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.ProductRecipe;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.entity.Supplier;
import com.project.Palaciossac.entity.SupplierMaterial;
import com.project.Palaciossac.repository.EmployeeRepository;
import com.project.Palaciossac.repository.ProductRecipeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.RawMaterialRepository;
import com.project.Palaciossac.repository.SupplierMaterialRepository;
import com.project.Palaciossac.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Carga datos de ejemplo al arrancar, para poder probar los endpoints
 * sin tener que crear todo a mano primero (solo si la base está vacía).
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierMaterialRepository supplierMaterialRepository;
    private final ProductRecipeRepository recipeRepository;

    private final CreateCustomerUseCase createCustomerUseCase;

    public DataInitializer(
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            RawMaterialRepository rawMaterialRepository,
            SupplierRepository supplierRepository,
            SupplierMaterialRepository supplierMaterialRepository,
            ProductRecipeRepository recipeRepository,
            CreateCustomerUseCase createCustomerUseCase) {

        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.supplierRepository = supplierRepository;
        this.supplierMaterialRepository = supplierMaterialRepository;
        this.recipeRepository = recipeRepository;
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }

        // Empleados
        employeeRepository.save(new Employee("Rosa Palacios", "Gerente de ventas", "987654321"));
        employeeRepository.save(new Employee("Mario Quispe", "Operario de confección", "987111222"));

        // Productos
        Product polo = productRepository.save(
                new Product("Polo básico", "Polo", "M", "Blanco", new BigDecimal("35.00"), 20));
        Product casaca = productRepository.save(
                new Product("Casaca denim", "Casaca", "L", "Azul", new BigDecimal("120.00"), 5));
        productRepository.save(
                new Product("Pantalón jogger", "Pantalón", "S", "Negro", new BigDecimal("80.00"), 0));

        // Materia prima
        RawMaterial tela = rawMaterialRepository.save(new RawMaterial("Tela algodón", "metro", new BigDecimal("200.000")));
        RawMaterial hilo = rawMaterialRepository.save(new RawMaterial("Hilo industrial", "cono", new BigDecimal("50.000")));
        RawMaterial botones = rawMaterialRepository.save(new RawMaterial("Botones", "unidad", new BigDecimal("500.000")));

        // Proveedores y precios de compra
        Supplier textiles = supplierRepository.save(
                new Supplier("Textiles del Sur SAC", "014445566", "ventas@textilessur.pe", "Av. Industrial 123, Lima"));
        Supplier insumos = supplierRepository.save(
                new Supplier("Insumos Confecciones EIRL", "012223344", "contacto@insumosconf.pe", "Jr. Gamarra 456, Lima"));

        supplierMaterialRepository.save(new SupplierMaterial(textiles, tela, new BigDecimal("12.50")));
        supplierMaterialRepository.save(new SupplierMaterial(insumos, tela, new BigDecimal("13.20")));
        supplierMaterialRepository.save(new SupplierMaterial(insumos, hilo, new BigDecimal("6.80")));
        supplierMaterialRepository.save(new SupplierMaterial(insumos, botones, new BigDecimal("0.15")));

        // Recetas de confección (cantidad de insumo por unidad fabricada)
        recipeRepository.save(new ProductRecipe(polo, tela, new BigDecimal("1.200")));
        recipeRepository.save(new ProductRecipe(polo, hilo, new BigDecimal("0.050")));
        recipeRepository.save(new ProductRecipe(casaca, tela, new BigDecimal("2.500")));
        recipeRepository.save(new ProductRecipe(casaca, hilo, new BigDecimal("0.100")));
        recipeRepository.save(new ProductRecipe(casaca, botones, new BigDecimal("6.000")));

        // Clientes (módulo hexagonal -> a través del caso de uso)
        createCustomerUseCase.create(new CreateCustomerCommand("Ana Torres", "45678912", "999111222", "Av. Arequipa 100, Lima"));
        createCustomerUseCase.create(new CreateCustomerCommand("Luis Ramírez", "20123456789", "999333444", "Jr. Unión 250, Lima"));

        System.out.println("Datos de ejemplo cargados: empleados, productos, insumos, proveedores, recetas y clientes.");
    }
}
