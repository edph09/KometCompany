package com.kometcompany.service;

import com.kometcompany.model.Inventory;
import com.kometcompany.repository.IInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * clase que implementa la logica de negocio de la entidad inventario  .
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Service
public class InventoryService {
    private IInventoryRepository inventoryRepository;

    /**
     * Constructor  .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    @Autowired
    public InventoryService(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    /**
     * metodo que me consulta el inventario segun la compania a travez del id de la compania  .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    public List<Inventory> getInventoryItemsByCompanyId(int companyId) {
        return inventoryRepository.findByCompanyId(companyId);
    }
    /**
     * metodo que me realiza el calculo del flete    .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    public double calculateFinalFreight(Inventory item) {
        double cubesPerBox = (item.getBoxType().getWidth() * item.getBoxType().getHeight()
                * item.getBoxType().getLength()) / 1728;
        double outboundFreight = (cubesPerBox * item.getCubesPerCarrier()) / item.getPack();
        return outboundFreight * (item.getProduct().getFreshCutValue() / 100);
    }

}
