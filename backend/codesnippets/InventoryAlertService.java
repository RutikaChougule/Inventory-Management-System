package backend.codesnippets;

// InventoryAlertService.java
@Service
public class InventoryAlertService {
    // Assume these are injected repositories
    private final InventoryRepository inventoryRepo;
    private final SupplierRepository supplierRepo;
    // Constructor
    public InventoryAlertService(InventoryRepository inventoryRepo,
                                 SupplierRepository supplierRepo) {
        this.inventoryRepo = inventoryRepo;
        this.supplierRepo = supplierRepo;
    }
    // Method to get low stock alerts for a company
    public List<LowStockAlertResponse> getLowStockAlerts(Long companyId) {
        // 1. Fetch inventory for company (all warehouses)
        List<Inventory> inventories = inventoryRepo.findByCompanyId(companyId);
        // 2. Prepare list for alerts
        List<LowStockAlertResponse> alerts = new ArrayList<>();
        // 3. Check each inventory item against threshold
        for (Inventory inv : inventories) {
            int threshold = getThresholdByProductType(inv.getProduct());
            if (inv.getQuantity() < threshold) {
                Supplier supplier =
                        supplierRepo.findPrimarySupplier(
                                inv.getProduct().getId(), companyId);
                // 3. Create alert response
                LowStockAlertResponse alert =
                        new LowStockAlertResponse(
                                inv.getProduct(),
                                inv.getWarehouse(),
                                inv.getQuantity(),
                                threshold,
                                supplier
                        );
                // 4. Add to alerts list
                alerts.add(alert);
            }
        }
        //
        return alerts;
    }
    // Business rule: threshold varies by product type
    private int getThresholdByProductType(Product product) {
        // Example thresholds
        if ("FAST_MOVING".equals(product.getType())) return 50;
        if ("SLOW_MOVING".equals(product.getType())) return 10;
        return 20;
    }
}
