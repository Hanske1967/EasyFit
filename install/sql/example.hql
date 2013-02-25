with all products in a recipe which name starts with 'bot'
select p.name as name from Recipe r, Product  p where p in elements(r.products) and r.name like 'bot%'
