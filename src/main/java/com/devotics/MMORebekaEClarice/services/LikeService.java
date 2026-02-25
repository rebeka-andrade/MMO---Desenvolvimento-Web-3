@Component
public class LikeService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    public ResponseEntity<?> likePost(Long postId){

        String email = jwtUtils.getAuthorizedId();

        User user = userRepo.findByEmail(email).get();
        Post post = postRepo.findById(postId).get();

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepo.save(like);

        return ResponseEntity.ok().build();
    }
}