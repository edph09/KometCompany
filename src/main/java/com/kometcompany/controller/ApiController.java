package com.kometcompany.controller;

import com.kometcompany.model.Customer;
import com.kometcompany.model.Inventory;
import com.kometcompany.service.CustomerService;
import com.kometcompany.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controlador , clase que da el acceso a los endpoints de la aplicacion  .
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CustomerService customerService;

    /**
     * Endpoint que muestra los productos discriminados por compania  calculando su flete final
     *  cubesPerBox =  (width * height * length) / 1728;
     *  outboundFreight =  (cubesPerCarrier  * cubesPerBox) / pack;
     *  finalFreight = outboundFreight * (freshCutValue/100)  .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    @GetMapping("/final-freight")
    public ResponseEntity<?> CompaniesFinalFreight(@RequestParam("companyId") int companyId){

        Map<String, Object> response = new HashMap<>();
        List<Inventory> inventoryItems;
        try {
            inventoryItems = inventoryService.getInventoryItemsByCompanyId(companyId);
        } catch (DataAccessException e){
            response.put("Message", "Error connecting to the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Map<String, Object>> productDetails = new ArrayList<>();

        for (Inventory item : inventoryItems) {
            Map<String, Object> productDetail = new HashMap<>();
            productDetail.put("productName", item.getProduct().getName());
            productDetail.put("basePrice", item.getBasePrice());
            productDetail.put("finalFreight", inventoryService.calculateFinalFreight(item));

            productDetails.add(productDetail);
        }

        if (!inventoryItems.isEmpty()){
            response.put("companyId", companyId);
            response.put("products", productDetails);
        }else {
            response.put("Message", "the Company was not found ");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /**
     * Endpoint que muestra los productos con su precio calculado segun la formula
     *  price =  basePrice - (basePrice * (markdown / 100))
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    @GetMapping("/calculate-price")
    public ResponseEntity<?> calculatePrice(@RequestParam int customerId) {
        Map<String, Object> response = new HashMap<>();
        Customer customer;

        try {
            customer = customerService.getCustomerById(customerId);
        } catch (DataAccessException e){
            response.put("Message", "Error connecting to the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (customer == null) {
            response.put("Message", "the Customer was not found ");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        BigDecimal markdown = customer.getMarkdown();
        List<Inventory> inventoryList = inventoryService.getInventoryItemsByCompanyId(customerId);
        List<Map<String, String>> result = new ArrayList<>();

        for (Inventory inventory : inventoryList) {
            BigDecimal basePrice = inventory.getBasePrice();
            BigDecimal price = basePrice.subtract(basePrice.multiply(markdown.divide(BigDecimal.valueOf(100))));

            Map<String, String> productInfo = new HashMap<>();
            productInfo.put("productName", inventory.getProduct().getName());
            productInfo.put("company", inventory.getCompany().getName());
            productInfo.put("price", price.toString());
            result.add(productInfo);
        }

        //Map<String, List> response = new HashMap<>();
        response.put("products",result);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * Endpoint que los productos con el codigo generado
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    @GetMapping("/product-code")
    public ResponseEntity<?> ProductCodeGenerator(@RequestParam("companyId") int companyId){

        Map<String, Object> errorMessage = new HashMap<>();
        List<Inventory> inventoryItems2;
        try {
            inventoryItems2 = inventoryService.getInventoryItemsByCompanyId(companyId);
        } catch (DataAccessException e){
            errorMessage.put("Message", "Error connecting to the database");
            errorMessage.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (inventoryItems2.isEmpty()) {
            errorMessage.put("Message", "the Product was not found ");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        //List<Inventory> inventoryItems2 = inventoryService.getInventoryItemsByCompanyId(companyId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Inventory item : inventoryItems2) {
            Map<String, Object> productDetail = new HashMap<>();
            productDetail.put("productName", item.getProduct().getName());
            productDetail.put("productCode", item.getProduct().generateProductCode());
            response.add(productDetail);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
