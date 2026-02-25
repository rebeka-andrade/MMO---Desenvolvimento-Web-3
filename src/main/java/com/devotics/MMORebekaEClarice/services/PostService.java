@Component
public class PostService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> create(NewPostDTO dto){

        String email = jwtUtils.getAuthorizedId();

        User user = userRepo.findByEmail(email).get();

        Post post = new Post();
        post.setContent(dto.getContent());
        post.setAuthor(user);

        postRepo.save(post);

        return ResponseEntity.ok().build();
    }
}