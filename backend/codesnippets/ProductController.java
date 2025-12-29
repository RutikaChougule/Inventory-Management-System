package backend.codesnippets;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @PostMapping
    @Transactional// To Ensure Object Consistency
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        try {
            // Validate required fields
            if (request.getName() == null || request.getName().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Missing required field: name"));
            }
            if (request.getSku() == null || request.getSku().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Missing required field: sku"));
            }
            if (request.getPrice() == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Missing required field: price"));
            }
            if (request.getWarehouseId() == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Missing required field: warehouse_id"));
            }
            if (request.getInitialQuantity() == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Missing required field: initial_quantity"));
            }
            // Create new product
            Product product = new Product();
            product.setName(request.getName());
            product.setSku(request.getSku());
            product.setPrice(request.getPrice());
            product.setWarehouseId(request.getWarehouseId());
            // Save product (flush to get ID)
            product = productRepository.saveAndFlush(product);
            // Create inventory record
            Inventory inventory = new Inventory();
            inventory.setProductId(product.getId());
            inventory.setWarehouseId(request.getWarehouseId());
            inventory.setQuantity(request.getInitialQuantity());
            inventoryRepository.save(inventory);

            // Both operations committed together due to @Transactional
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product created");
            response.put("product_id", product.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // To Check Error Handling and Ensure No Partial Commits
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
