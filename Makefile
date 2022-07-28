alltests:
	cd web && make test
	cd server && make test
	cd web && make e2e
