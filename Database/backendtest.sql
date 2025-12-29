create table Company (
  company_id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  created_at TIMESTAMP
);
Create  table Warehouse (
  warehouse_id BIGINT PRIMARY KEY,
  company_id BIGINT,
  location VARCHAR(255),
  FOREIGN KEY (company_id) REFERENCES Company(company_id)
);
Create Table InventoryHistory (
  history_id BIGINT PRIMARY KEY,
  inventory_id BIGINT,
  change_type VARCHAR(50), -- ADD, REMOVE, ADJUST
  quantity_change INT,
  changed_at TIMESTAMP,
  FOREIGN KEY (inventory_id) REFERENCES Inventory(inventory_id)
);
Create Table Product (
  product_id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  sku VARCHAR(100) UNIQUE,
  is_bundle BOOLEAN
);
Create Table Inventory (
  inventory_id BIGINT PRIMARY KEY,
  warehouse_id BIGINT,
  product_id BIGINT,
  quantity INT,
  UNIQUE (warehouse_id, product_id),
  FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id),
  FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
Create Table Supplier (
  supplier_id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  contact_info VARCHAR(255)
);
Create Table Supplier_Product (
  supplier_id BIGINT,
  product_id BIGINT,
  company_id BIGINT,
  PRIMARY KEY (supplier_id, product_id, company_id),
  FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id),
  FOREIGN KEY (product_id) REFERENCES Product(product_id),
  FOREIGN KEY (company_id) REFERENCES Company(company_id)
);
Create Table Product_Bundle (
  bundle_id BIGINT,
  child_product_id BIGINT,
  quantity INT,
  PRIMARY KEY (bundle_id, child_product_id),
  FOREIGN KEY (bundle_id) REFERENCES Product(product_id),
  FOREIGN KEY (child_product_id) REFERENCES Product(product_id)
);