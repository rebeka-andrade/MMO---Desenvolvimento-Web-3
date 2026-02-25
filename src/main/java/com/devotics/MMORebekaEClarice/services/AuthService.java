@Component
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<?> register(RegisterDTO dto){

        if(userRepo.findByEmail(dto.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email já existe");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole("USER");

        userRepo.save(user);

        return ResponseEntity.ok("Usuário criado!");
    }

    public ResponseEntity<?> login(LoginDTO login){

        Optional<User> userOpt = userRepo.findByEmail(login.getEmail());

        if(userOpt.isPresent()){

            User user = userOpt.get();

            if(encoder.matches(login.getPassword(), user.getPassword())){
                return ResponseEntity.ok(
                    jwtUtils.generateToken(user.getEmail(), user.getRole())
                );
            }
        }

        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
}