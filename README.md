Pour les articles
Action	URL	Méthode	Mapping Spring	Template
Afficher formulaire création	/article/new	GET	@GetMapping("/new")	add-article.html
Créer article	/article/new	POST	@PostMapping("/new")	redirige vers liste ou confirmation
Lister articles	/article/list	GET	@GetMapping("/list")	article-list.html
Mettre à jour article	/article/update/{id}	PUT	@PutMapping("/update/{id}")	JSON ou redirect selon config
Supprimer article	/article/delete/{id}	DELETE	@DeleteMapping("/delete/{id}")	JSON ou redirect
Pour les utilisateurs
Action	URL	Méthode	Mapping Spring
Créer utilisateur	/user-app/register	POST	@PostMapping("/register")
Lister tous les utilisateurs	/user-app	GET	@GetMapping
Mettre à jour utilisateur	/user-app/update/{id}	PUT	@PutMapping("/update/{id}")
Supprimer utilisateur	/user-app/delete/{id}	DELETE	@DeleteMapping("/delete/{id}")