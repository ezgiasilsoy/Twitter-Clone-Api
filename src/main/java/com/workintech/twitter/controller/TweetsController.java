import com.workintech.twitter.dto.request.TweetsRequestDto;
import com.workintech.twitter.dto.response.TweetsResponseDto;
import com.workintech.twitter.service.TweetsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetsController {

    private final TweetsService tweetsService;

    // EASY
    // POST http://localhost:3000/tweet
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetsResponseDto createTweet(
            @Valid @RequestBody TweetsRequestDto dto,
            Principal principal
    ) {
        return tweetsService.createTweet(principal, dto);
    }

    // EASY
    // GET http://localhost:3000/tweet/findByUserId
    @GetMapping("/findByUserId")
    @ResponseStatus(HttpStatus.OK)
    public List<TweetsResponseDto> findByUserId(Principal principal) {
        return tweetsService.findMyTweets(principal);
    }

    // EASY
    // GET http://localhost:3000/tweet/findById?id=1
    @GetMapping("/findById")
    @ResponseStatus(HttpStatus.OK)
    public TweetsResponseDto findById(@RequestParam Long id) {
        return tweetsService.findTweetById(id);
    }

    // EASY
    // PUT http://localhost:3000/tweet/:id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TweetsResponseDto updateTweet(
            @PathVariable Long id,
            @Valid @RequestBody TweetsRequestDto dto,
            Principal principal
    ) {
        return tweetsService.updateTweet(id, principal, dto);
    }

    // EASY
    // DELETE http://localhost:3000/tweet/:id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(
            @PathVariable Long id,
            Principal principal
    ) {
        tweetsService.deleteTweet(id, principal);
    }
}
