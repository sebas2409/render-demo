vcl 4.1;
import std;

backend default {
    .host = "nginx";
    .port = "8080";
}

sub vcl_recv {
    set req.backend_hint = default;
    if (req.url ~ "^/api") {
        return (pass);
    }
    return (hash);
}


sub vcl_backend_response {
    set beresp.do_esi = true;
    set beresp.ttl = 0s;
}