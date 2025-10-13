package com.pingidentity.adapter.idp.util.solid;

/**
 * S - Single Responsibility Principle (SRP)
 * Uma classe deve ter apenas um motivo para mudar.
 */

// ❌ RUIM: Classe com múltiplas responsabilidades
class UserManagerBad {
    public void saveUser(String user) {
        // Lógica de validação
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user");
        }

        // Lógica de persistência
        System.out.println("Saving user to database: " + user);

        // Lógica de notificação
        System.out.println("Sending welcome email to: " + user);
    }
}

// ✅ BOM: Cada classe tem uma única responsabilidade
class UserValidator {
    public void validate(String user) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user");
        }
    }
}

class UserRepository {
    public void save(String user) {
        System.out.println("Saving user to database: " + user);
    }
}

class EmailService {
    public void sendWelcomeEmail(String user) {
        System.out.println("Sending welcome email to: " + user);
    }
}

class UserManager {
    private final UserValidator validator;
    private final UserRepository repository;
    private final EmailService emailService;

    public UserManager(UserValidator validator, UserRepository repository, EmailService emailService) {
        this.validator = validator;
        this.repository = repository;
        this.emailService = emailService;
    }

    public void saveUser(String user) {
        validator.validate(user);
        repository.save(user);
        emailService.sendWelcomeEmail(user);
    }
}

// -------

/**
 * O - Open/Closed Principle (OCP)
 * Classes devem ser abertas para extensão, mas fechadas para modificação.
 */

// ❌ RUIM: Modificar a classe para adicionar novos tipos
class EncryptorBad {
    public String encrypt(String data, String type) {
        if ("AES".equals(type)) {
            return "AES encrypted: " + data;
        } else if ("RSA".equals(type)) {
            return "RSA encrypted: " + data;
        }
        // Para adicionar novo algoritmo, precisa modificar esta classe
        throw new UnsupportedOperationException("Unsupported encryption type");
    }
}

// ✅ BOM: Extensível sem modificação
interface Encryptor {
    String encrypt(String data);
}

class AESEncryptor implements Encryptor {
    @Override
    public String encrypt(String data) {
        return "AES encrypted: " + data;
    }
}

class RSAEncryptor implements Encryptor {
    @Override
    public String encrypt(String data) {
        return "RSA encrypted: " + data;
    }
}

// Novo algoritmo sem modificar código existente
class BlowfishEncryptor implements Encryptor {
    @Override
    public String encrypt(String data) {
        return "Blowfish encrypted: " + data;
    }
}

class EncryptionService {
    private final Encryptor encryptor;

    public EncryptionService(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String processData(String data) {
        return encryptor.encrypt(data);
    }
}


// ------


/**
 * L - Liskov Substitution Principle (LSP)
 * Objetos de uma classe derivada devem poder substituir objetos da classe base
 * sem alterar o comportamento do programa.
 */

// ❌ RUIM: Violação do LSP
abstract class Bird {
    public abstract void fly();
}

class Eagle extends Bird {
    @Override
    public void fly() {
        System.out.println("Eagle is flying high!");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        // Viola LSP - pinguim não pode voar!
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

// ✅ BOM: Respeitando o LSP
abstract class Animal {
    public abstract void move();
}

abstract class FlyingBird extends Animal {
    public abstract void fly();

    @Override
    public void move() {
        fly();
    }
}

abstract class NonFlyingBird extends Animal {
    public abstract void walk();

    @Override
    public void move() {
        walk();
    }
}

class EagleGood extends FlyingBird {
    @Override
    public void fly() {
        System.out.println("Eagle is flying high!");
    }
}

class PenguinGood extends NonFlyingBird {
    @Override
    public void walk() {
        System.out.println("Penguin is walking on ice!");
    }
}

// Exemplo prático no contexto do adapter
abstract class AuthenticationMethod {
    public abstract boolean authenticate(String username, String credential);
}

class PasswordAuth extends AuthenticationMethod {
    @Override
    public boolean authenticate(String username, String credential) {
        // Lógica de autenticação por senha
        return credential != null && credential.length() >= 8;
    }
}

class TokenAuth extends AuthenticationMethod {
    @Override
    public boolean authenticate(String username, String credential) {
        // Lógica de autenticação por token
        return credential != null && credential.startsWith("Bearer ");
    }
}

// ------


/**
 * I - Interface Segregation Principle (ISP)
 * Clientes não devem ser forçados a depender de interfaces que não usam.
 */

// ❌ RUIM: Interface muito grande ("fat interface")
interface WorkerBad {
    void work();
    void eat();
    void sleep();
    void payTaxes();
    void attendMeetings();
}

class HumanWorker implements WorkerBad {
    @Override
    public void work() { System.out.println("Human working..."); }

    @Override
    public void eat() { System.out.println("Human eating..."); }

    @Override
    public void sleep() { System.out.println("Human sleeping..."); }

    @Override
    public void payTaxes() { System.out.println("Human paying taxes..."); }

    @Override
    public void attendMeetings() { System.out.println("Human attending meetings..."); }
}

class Robot implements WorkerBad {
    @Override
    public void work() { System.out.println("Robot working..."); }

    @Override
    public void eat() {
        // Robot não come! Interface forçou implementação desnecessária
        throw new UnsupportedOperationException("Robots don't eat!");
    }

    @Override
    public void sleep() {
        // Robot não dorme!
        throw new UnsupportedOperationException("Robots don't sleep!");
    }

    @Override
    public void payTaxes() {
        // Robot não paga impostos!
        throw new UnsupportedOperationException("Robots don't pay taxes!");
    }

    @Override
    public void attendMeetings() {
        // Robot não participa de reuniões!
        throw new UnsupportedOperationException("Robots don't attend meetings!");
    }
}

// ✅ BOM: Interfaces segregadas e específicas
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

interface Taxpayer {
    void payTaxes();
}

interface MeetingAttendee {
    void attendMeetings();
}

class HumanWorkerGood implements Workable, Eatable, Sleepable, Taxpayer, MeetingAttendee {
    @Override
    public void work() { System.out.println("Human working..."); }

    @Override
    public void eat() { System.out.println("Human eating..."); }

    @Override
    public void sleep() { System.out.println("Human sleeping..."); }

    @Override
    public void payTaxes() { System.out.println("Human paying taxes..."); }

    @Override
    public void attendMeetings() { System.out.println("Human attending meetings..."); }
}

class RobotGood implements Workable {
    @Override
    public void work() { System.out.println("Robot working..."); }
    // Robot só implementa o que realmente faz!
}

// Exemplo prático no contexto do adapter
interface UserDataProvider {
    String getUsername();
    String getPassword();
}

interface EncryptedDataProvider {
    String getEncryptedPassword();
}

interface ExtendedAttributesProvider {
    java.util.Map<String, String> getUserAttributes();
}

// Cliente que só precisa de dados básicos
class BasicAuthenticator {
    public boolean authenticate(UserDataProvider user) {
        return user.getUsername() != null && user.getPassword() != null;
    }
}

// Cliente que precisa de dados criptografados
class EncryptedAuthenticator {
    public boolean authenticate(UserDataProvider user, EncryptedDataProvider encrypted) {
        return user.getUsername() != null && encrypted.getEncryptedPassword() != null;
    }
}

// ------

/**
 * D - Dependency Inversion Principle (DIP)
 * Módulos de alto nível não devem depender de módulos de baixo nível.
 * Ambos devem depender de abstrações.
 */

// ❌ RUIM: Dependência direta de implementações concretas
class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class EmailNotifier {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class UserServiceBad {
    private MySQLDatabase database; // Dependência direta!
    private EmailNotifier notifier; // Dependência direta!

    public UserServiceBad() {
        this.database = new MySQLDatabase(); // Acoplamento forte!
        this.notifier = new EmailNotifier(); // Acoplamento forte!
    }

    public void createUser(String userData) {
        database.save(userData);
        notifier.send("User created: " + userData);
    }
}

// ✅ BOM: Dependendo de abstrações
interface Database {
    void save(String data);
}

interface Notifier {
    void send(String message);
}

class MySQLDatabaseGood implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

class EmailNotifierGood implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class UserServiceGood {
    private final Database database; // Depende da abstração!
    private final Notifier notifier; // Depende da abstração!

    // Injeção de dependência via construtor
    public UserServiceGood(Database database, Notifier notifier) {
        this.database = database;
        this.notifier = notifier;
    }

    public void createUser(String userData) {
        database.save(userData);
        notifier.send("User created: " + userData);
    }
}

// Exemplo prático no contexto do seu adapter
interface DecryptionService {
    String decrypt(String encryptedData, String clientId);
}

interface UserValidator {
    boolean isValid(String username, String password);
}

interface AttributeMapper {
    java.util.Map<String, Object> mapAttributes(String username, String decryptedValue);
}

class AdapterService {
    private final DecryptionService decryptionService;
    private final UserValidator userValidator;
    private final AttributeMapper attributeMapper;

    // Injeção de dependência - fácil de testar e trocar implementações
    public AdapterService(DecryptionService decryptionService,
                         UserValidator userValidator,
                         AttributeMapper attributeMapper) {
        this.decryptionService = decryptionService;
        this.userValidator = userValidator;
        this.attributeMapper = attributeMapper;
    }

    public java.util.Map<String, Object> authenticate(String username, String password,
                                                     String encryptedData, String clientId) {
        if (!userValidator.isValid(username, password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String decryptedValue = decryptionService.decrypt(encryptedData, clientId);
        return attributeMapper.mapAttributes(username, decryptedValue);
    }
}

// Implementações concretas
class BradescoDecryptionService implements DecryptionService {
    @Override
    public String decrypt(String encryptedData, String clientId) {
        // Sua lógica atual com DecryptData
        return "decrypted_value";
    }
}

class SimpleUserValidator implements UserValidator {
    @Override
    public boolean isValid(String username, String password) {
        return username != null && password != null && password.length() >= 6;
    }
}

class OIDCAttributeMapper implements AttributeMapper {
    @Override
    public java.util.Map<String, Object> mapAttributes(String username, String decryptedValue) {
        java.util.Map<String, Object> attributes = new java.util.HashMap<>();
        attributes.put("sub", username);
        attributes.put("username", username);
        attributes.put("DECRYPTED_VALUE", decryptedValue);
        return attributes;
    }
}
