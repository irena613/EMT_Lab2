//package mk.ukim.finki.emt.lab.service.domain.impl;
//
//import mk.ukim.finki.emt.lab.model.domain.User;
//import mk.ukim.finki.emt.lab.model.enums.Role;
//import mk.ukim.finki.emt.lab.model.exceptions.InvalidArgumentsException;
//import mk.ukim.finki.emt.lab.model.exceptions.InvalidUserCredentialsException;
//import mk.ukim.finki.emt.lab.model.exceptions.InvalidUsernameOrPasswordException;
//import mk.ukim.finki.emt.lab.model.exceptions.PasswordsDoNotMatchException;
//import mk.ukim.finki.emt.lab.model.exceptions.UsernameAlreadyExistsException;
//import mk.ukim.finki.emt.lab.repository.UserRepository;
//import mk.ukim.finki.emt.lab.service.domain.UserService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
//                username));
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
//                username));
//    }
//
//    @Override
//    public User register(
//            String username,
//            String password,
//            String repeatPassword,
//            String name,
//            String surname,
//            Role userRole
//    ) {
//        if (username == null || username.isEmpty() || password == null || password.isEmpty())
//            throw new RuntimeException("Username or password cannot be empty");
//        if (!password.equals(repeatPassword)) throw new RuntimeException("Passwords do not match");
//        if (userRepository.findByUsername(username).isPresent())
//            throw new RuntimeException("Username is already in use");
//        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User login(String username, String password) {
//        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
//            throw new RuntimeException("Username or password cannot be empty");
//        }
//        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
//                RuntimeException::new);
//    }
//
//}