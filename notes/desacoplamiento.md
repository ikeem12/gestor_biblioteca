# desacomplamiento

Desacoplar significa evitar que una parte del sistema dependa directamente de otra parte concreta.
En otras palabras, separar responsabilidades para que un cambio en una capa no rompa toda la aplicación.

## Ejemplo simple sin desacoplar

Imaginar que en la capa de negocio (UserService) se escribe esto:

````java
public class UserService {
    private final UserRepository repo = new UserRepository();

    public void registrarUsuario(User user) {
        repo.save(user);
    }
}
````

Aquí UserService está acoplado directamente a UserRepository.
Si mañana se quiere cambiar UserRepository para usar MongoDB en vez de MySQL, se tendria que modificar UserService.

**O sea, un cambio abajo rompe arriba → malo.**

## Ejemplo con desacoplamiento (usando interfaz)

````java
public interface IUserRepository {
    void save(User user);
}

public class UserRepository implements IUserRepository {
    @Override
    public void save(User user) {
        // lógica real de guardado
    }
}

public class UserService {
    private final IUserRepository repo;

    // Inyección de dependencias (no lo creo yo, me lo pasan desde fuera)
    public UserService(IUserRepository repo) {
        this.repo = repo;
    }

    public void registrarUsuario(User user) {
        repo.save(user);
    }
}
````
Ahora UserService ya no depende de una implementación concreta, sino de una interfaz (IUserRepository).
Si mañana se quiere un UserRepositoryMongo, simplemente lo implemento y lo inyecto.
UserService ni se entera.

### Beneficios de desacoplar

- **Flexibilidad** → puedo cambiar una implementación sin romper el resto.

- **Testabilidad** → puedo pasar un FakeRepository o MockRepository en las pruebas.

- **Reutilización** → la capa de negocio se puede usar en otro proyecto con otro almacenamiento.