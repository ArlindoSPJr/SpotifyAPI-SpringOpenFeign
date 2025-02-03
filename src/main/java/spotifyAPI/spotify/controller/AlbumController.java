package spotifyAPI.spotify.controller;

import spotifyAPI.spotify.client.Album;
import spotifyAPI.spotify.client.AlbumSpotifyClient;
import spotifyAPI.spotify.client.AuthSpotifyClient;
import spotifyAPI.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping(value = "/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        var request = new LoginRequest(
                "client_credentials",
                "f13590f5b4b14bc29fd89be5a97ef099",
                "0a0fca97878d408d9630e22422ca10fb"
        );

        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getRealeses("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }


}
