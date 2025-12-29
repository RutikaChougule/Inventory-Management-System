package backend.codesnippets;

// InventoryAlertController.java
@RestController
@RequestMapping("/api/companies")
public class InventoryAlertController {
    // Service to handle inventory alerts
    private final InventoryAlertService alertService;

    public InventoryAlertController(InventoryAlertService alertService) {
        this.alertService = alertService;
    }
    // Endpoint to get low stock alerts for a company
    @GetMapping("/{companyId}/low-stock-alerts")
    // Method to handle GET request for low stock alerts
    public ResponseEntity<Map<String, Object>> getLowStockAlerts(
            @PathVariable Long companyId) // here we provide the company id {
    // Fetch low stock alerts from the service
    List<LowStockAlertResponse> alerts = alertService.getLowStockAlerts(companyId);
    //map response
    Map<String, Object> response = new HashMap<>();
        response.put("alerts", alerts);
        response.put("total_alerts", alerts.size());

        return ResponseEntity.ok(response);
}
}
