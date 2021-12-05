# Architecture

### FRONTEND:
Tailwind Css

React Native

Typescript 

Feather Icons

### Backend: Custom Signaling WebServer made with Vert.x
Tools:
    Vert.x
    
    MongoDB
    
    Gradle
#### Notes:

    A WebServer?
        Software that serves web content. Anything that can be on the web and be transferred on the web.
        Uses the HTTP protocol
        Uses Static; bland content and Dynamic; responsive and highly changable tings. Content
        Used to Host web pages
    How does a web server work?
        The client device makes a request to wherever the server is running (AWS) and it the server processes that request and return what its supossed to.
    What does a web server do?
        1. Listens
            waits for requests. Does nothing until we get a request
        2. On a port
            http port 80
        3. For a request
            literelly a request like post, get etc. 
            HTTP request lol
            HTTP and FTP are collective meaning you cant mix them up
        4. Transport protocol
             TCP protocol a shipper kinda service.
        5. Response
            We get on abck from the server.
            Suppose we have a button to show us a numebr one.
            Once we press the button... we send a request GJ
            then the server gets that and goes to its code and handles the task it supposed to do when the button is pressed: in this case to show the nnumber 1.
        6. Resource
            Routing
            /LOL/stoopid/dank/dum-dum
            Static routing is basically just like a file system <== this is boring doe this is used when we have stuff were data doesnt change often.
            Dynamic routing is when we have data that often changes like a users feed or profile
                You cant do this statically so you woukld save the current state of that to a database and update it dynamically.
                A place where our app can start and then based on requests we can make a collective redirect to the server that the user wants to go to.

    What is a database?
    
        Basically its an organized collection of data.
        mySQL is a relational database
            It is filled with tables that have rows and columns
            
## Backend Notes

##### Basic Vert.X struct 

Vert.x is based around lightweight actors, called Verticles.

Verticle is an isolated unit of work, that can scale independently.
a;slkdfa;lskdjfja;lskdjf
Usually, actor model requires actors to have a concept called “incoming mailbox” which is usually a queue (more specifically blocking buffered queue).

So, if one actor wants some work to be done by another actor, it will push a message into its mailbox.

What work this may be? Let’s take an animal shelter as an example. We’ll have one service that gets requests for registration of new animals in the shelter, and another, that actually stores them into some kind of a database.

#### Event Bus
Once we have too many actoers doing to many task, our architecture gets more complicated. It's hard to have all the actors communicate with everyone. 
Hence Vert.x approches this problem with a new mechanism => the Event Bus

#### Event Loop/Multi Reactor
The Event Loop is like a reactor design pattern copy.

It’s goal is to continuously check for new events, and each time a new event comes in, to quickly dispatch it to someone who knows how to handle it.

But by using only one thread to consume all the events, we’re basically not making the best use of our hardware. Node.js applications, for example, often spawn multiple processes to address that issue.

While providing good isolation, processes are expensive. Vert.x uses multiple threads instead, which are cheaper in terms of system resources.

As we know we have a central handler that ships each request to its handler but how do we do that?
    - Threads; We can use a Multi-Thread Option and go on further but thats gets harder when wehave tons of requests poring in from clients.
        - Threads are expensive
    - Event Loop; Pro­cess­ing more con­cur­rent con­nec­tions with less threads is pos­si­ble when you use asyn­chro­nous I/O. In­stead of block­ing a thread when an I/O op­er­a­tion oc­curs, we move on to an­other task which is ready to progress, and re­sume the ini­tial task later when it is ready. 

Vert.x mul­ti­plexes con­cur­rent work­loads using event loops.



What is Vert.X :

    Eclipse Vert.x and reactive in just a few words
    Eclipse Vert.x is a tool-​kit for build­ing re­ac­tive ap­pli­ca­tions on the JVM. Re­ac­tive ap­pli­ca­tions are both scal­able as work­loads grow, and re­silient when fail­ures arise. A re­ac­tive ap­pli­ca­tion is re­spon­sive as it keeps la­tency under con­trol by mak­ing ef­fi­cient usage of sys­tem re­sources, and by pro­tect­ing it­self from er­rors.

    Microservices
    Vert.x is backed by a large ecosys­tem of re­ac­tive mod­ules with just any­thing you need when writ­ing mod­ern ser­vices: a com­pre­hen­sive web stack, re­ac­tive data­base dri­vers, mes­sag­ing, event streams, clus­ter­ing, met­rics, dis­trib­uted trac­ing and more.

    Vert.x is a tool-​kit and not a frame­work that ships with black magic: what you write is ac­tu­ally what you get to ex­e­cute, as sim­ple as that.

    So what makes Vert.x a great op­tion for writ­ing your next cloud-​native or twelve-​factor app?

    In the beginning, there were threads…
    The clas­sic ap­proach to con­cur­rency is to use threads. Mul­ti­ple threads can live within a sin­gle process, per­form con­cur­rent work, and share the same mem­ory space.

    Threads
    Most ap­pli­ca­tion and ser­vice de­vel­op­ment frame­works are based on multi-​threading. On the sur­face, the model of hav­ing 1 thread per con­nec­tion is re­as­sur­ing be­cause de­vel­op­ers can rely on tra­di­tional im­per­a­tive style code.

    This is fine, es­pe­cially if you for­get about those silly mis­takes you can make with multi-​threading and mem­ory ac­cess …

    Multi-threading is “simple” but limited
    What hap­pens as the work­load grows be­yond mod­er­ate work­loads? (see the C10k prob­lem)

    The an­swer is sim­ple: you start mak­ing your op­er­at­ing sys­tem ker­nel suf­fer be­cause there is too much con­text switch­ing work with in-​flight re­quests.

    Blocking I/O
    Some of your threads will be blocked be­cause they are wait­ing on I/O op­er­a­tions to com­plete, some will be ready to han­dle I/O re­sults, and some will be in the mid­dle of doing CPU-​intensive tasks.

    Mod­ern ker­nels have very good sched­ulers, but you can­not ex­pect them to deal with 50 000 threads as eas­ily as they would do with 5 000. Also, threads aren’t cheap: cre­at­ing a thread takes a few mil­lisec­onds, and a new thread eats about 1MB of mem­ory.

    Asynchronous programming: scalability and resource efficiency
    Pro­cess­ing more con­cur­rent con­nec­tions with less threads is pos­si­ble when you use asyn­chro­nous I/O. In­stead of block­ing a thread when an I/O op­er­a­tion oc­curs, we move on to an­other task which is ready to progress, and re­sume the ini­tial task later when it is ready.

    Vert.x mul­ti­plexes con­cur­rent work­loads using event loops.

    Event loop
    Code that runs on event loops should not per­form block­ing I/O or lengthy pro­cess­ing. But don’t worry if you have such code: Vert.x has worker threads and APIs to process events back on an event loop.

    Pick the best asynchronous programming model for your problem domain
    We know that asyn­chro­nous pro­gram­ming re­quires more ef­forts. At the core of Vert.x, we sup­port call­backs and promises/fu­tures, the lat­ter being a sim­ple and el­e­gant model for chain­ing asyn­chro­nous op­er­a­tions.

    Ad­vanced re­ac­tive pro­gram­ming is pos­si­ble with Rx­Java, and if you pre­fer some­thing closer to tra­di­tional im­per­a­tive pro­gram­ming, then we are happy to pro­vide you with first-​class sup­port of Kotlin corou­tines.

    Asynchronous programming
    Vert.x sup­ports many asyn­chro­nous pro­gram­ming mod­els: choose what works best for each prob­lem you need to solve!

    Don’t let failures ruin responsiveness
    Fail­ures hap­pen all the time. A data­base will go down, the net­work will go down, or some ser­vice you de­pend on will be­come un­re­spon­sive.

    Failures
    Vert.x of­fers tools to keep la­tency under con­trol, in­clud­ing a sim­ple and ef­fi­cient cir­cuit breaker.

    A rich ecosystem
    The Eclipse Vert.x stack con­tains mod­ules for build­ing mod­ern, end-​to-end re­ac­tive ser­vices. From ef­fi­cient re­ac­tive data­base clients to event stream­ing, mes­sag­ing and web stacks, the Eclipse Vert.x project has you cov­ered:

    Ecosystem
    Can’t find what you are look­ing for?

    The Re­ac­ti­verse is a larger com­mu­nity around the re­ac­tive ecosys­tems where you can find more client and mod­ules.
    The Vert.x Awe­some repos­i­tory pro­vides links to even more in­ter­est­ing projects from the larger open-​source com­mu­nity!

### Vert.x Core 
    The functionality in core is fairly low level - you won’t find stuff like database access, authorisation or high level web functionality here - that kind of stuff you’ll find in Vert.x ext (extensions).

    Vert.x core is small and lightweight. You just use the parts you want. It’s also entirely embeddable in your existing applications - we don’t force you to structure your applications in a special way just so you can use Vert.x.

    You can use core from any of the other languages that Vert.x supports. But here’s a cool bit - we don’t force you to use the Java API directly from, say, JavaScript or Ruby - after all, different languages have different conventions and idioms, and it would be odd to force Java idioms on Ruby developers (for example). Instead, we automatically generate an idiomatic equivalent of the core Java APIs for each language.

    From now on we’ll just use the word core to refer to Vert.x core.

    If you are using Maven or Gradle, add the following dependency to the dependencies section of your project descriptor to access the Vert.x Core API:

    Maven (in your pom.xml):

    <dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-core</artifactId>
    <version>4.1.1</version>
    </dependency>
    Gradle (in your build.gradle file):

    dependencies {
    compile 'io.vertx:vertx-core:4.1.1'
    }
    Let’s discuss the different concepts and features in core.

    In the beginning there was Vert.x
    You can’t do much in Vert.x-land unless you can communicate with a 
    Vertx
    object!

    It’s the control centre of Vert.x and is how you do pretty much everything, including creating clients and servers, getting a reference to the event bus, setting timers, as well as many other things.

    So how do you get an instance?

    If you’re embedding Vert.x then you simply create an instance as follows:

    Vertx vertx = Vertx.vertx();
    Note
    Most applications will only need a single Vert.x instance, but it’s possible to create multiple Vert.x instances if you require, for example, isolation between the event bus or different groups of servers and clients.
    Specifying options when creating a Vertx object
    When creating a Vert.x object you can also specify options if the defaults aren’t right for you:

    Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
    The 
    VertxOptions
    object has many settings and allows you to configure things like clustering, high availability, pool sizes and various other settings.

    Creating a clustered Vert.x object
    If you’re creating a clustered Vert.x (See the section on the event bus for more information on clustering the event bus), then you will normally use the asynchronous variant to create the Vertx object.

    This is because it usually takes some time (maybe a few seconds) for the different Vert.x instances in a cluster to group together. During that time, we don’t want to block the calling thread, so we give the result to you asynchronously.

    ###### Are you fluent?
    You may have noticed that in the previous examples a fluent API was used.

    A fluent API is where multiple methods calls can be chained together. For example:

    request.response().putHeader("Content-Type", "text/plain").end("some text");
    This is a common pattern throughout Vert.x APIs, so get used to it.

    Chaining calls like this allows you to write code that’s a little bit less verbose. Of course, if you don’t like the fluent approach we don’t force you to do it that way, you can happily ignore it if you prefer and write your code like this:

    HttpServerResponse response = request.response();
    response.putHeader("Content-Type", "text/plain");
    response.write("some text");
    response.end();

    ###### Don’t call us, we’ll call you.
    The Vert.x APIs are largely event driven. This means that when things happen in Vert.x that you are interested in, Vert.x will call you by sending you events.

    Some example events are:

    a timer has fired

    some data has arrived on a socket,

    some data has been read from disk

    an exception has occurred

    an HTTP server has received a request

    You handle events by providing handlers to the Vert.x APIs. For example to receive a timer event every second you would do:

    vertx.setPeriodic(1000, id -> {
    // This handler will get called every second
    System.out.println("timer fired!");
    });
    Or to receive an HTTP request:

    server.requestHandler(request -> {
    // This handler will be called every time an HTTP request is received at the server
    request.response().end("hello world!");
    });
    Some time later when Vert.x has an event to pass to your handler Vert.x will call it asynchronously.

    This leads us to some important concepts in Vert.x:

###### Don’t block me!
    With very few exceptions (i.e. some file system operations ending in 'Sync'), none of the APIs in Vert.x block the calling thread.

    If a result can be provided immediately, it will be returned immediately, otherwise you will usually provide a handler to receive events some time later.

    Because none of the Vert.x APIs block threads that means you can use Vert.x to handle a lot of concurrency using just a small number of threads.

    With a conventional blocking API the calling thread might block when:

    Reading data from a socket

    Writing data to disk

    Sending a message to a recipient and waiting for a reply.

    …​ Many other situations

    In all the above cases, when your thread is waiting for a result it can’t do anything else - it’s effectively useless.

    This means that if you want a lot of concurrency using blocking APIs then you need a lot of threads to prevent your application grinding to a halt.

    Threads have overhead in terms of the memory they require (e.g. for their stack) and in context switching.

    For the levels of concurrency required in many modern applications, a blocking approach just doesn’t sca

###### Multi Reactor

    The Event Loop is like a reactor design pattern copy.

    It’s goal is to continuously check for new events, and each time a new event comes in, to quickly dispatch it to someone who knows how to handle it.

    But by using only one thread to consume all the events, we’re basically not making the best use of our hardware. Node.js applications, for example, often spawn multiple processes to address that issue.

    While providing good isolation, processes are expensive. Vert.x uses multiple threads instead, which are cheaper in terms of system resources.

    As we know we have a central handler that ships each request to its handler but how do we do that?
        - Threads; We can use a Multi-Thread Option and go on further but thats gets harder when wehave tons of requests poring in from clients.
            - Threads are expensive
        - Event Loop; Pro­cess­ing more con­cur­rent con­nec­tions with less threads is pos­si­ble when you use asyn­chro­nous I/O. In­stead of block­ing a thread when an I/O op­er­a­tion oc­curs, we move on to an­other task which is ready to progress, and re­sume the ini­tial task later when it is ready. 

    Vert.x mul­ti­plexes con­cur­rent work­loads using event loops.
###### The Golden Rule - Don’t Block the Event Loop
    lol dont break da loop das all.

### Vert.x Web
    Vert.x Core is the real core of application that vert.x web runs on but there are also added feutures specifically for webserver dev.
    You can use Vert.x-Web to create classic server-side web applications, RESTful web applications, 'real-time' (server push) web applications, or any other kind of web application you can think of. Vert.x-Web doesn’t care. It’s up to you to chose the type of app you prefer, not Vert.x-Web

    Vert.x-Web is a great fit for writing RESTful HTTP micro-service

    Some of the key features of Vert.x-Web include:

        Routing (based on method, path, etc)

        Regular expression pattern matching for paths

        Extraction of parameters from paths

        Content negotiation

        Request body handling

        Body size limits

        Multipart forms

        Multipart file uploads

        Sub routers

        Session support - both local (for sticky sessions) and clustered (for non sticky)

        CORS (Cross Origin Resource Sharing) support

        Error page handler

        HTTP Basic/Digest Authentication

        Redirect based authentication

        Authorization handlers

        JWT/OAuth2 based authorization

        User/role/permission authorization

        Favicon handling

        Template support for server side rendering, including support for the following template engines out of the box:

        Handlebars

        Jade,

        MVEL

        Thymeleaf

        Apache FreeMarker

        Pebble

        Rocker

        Response time handler

        Static file serving, including caching logic and directory listing.

        Request timeout support

        SockJS support

        Event-bus bridge

        CSRF Cross Site Request Forgery

        VirtualHost
### Basic Vert.x Web Concepts

##### Router
    A router is like the router that has "Routes"    
    The Default routes is zero
    When we get a HTTP request for a new page or an externel link we pass the request to the requested route.

##### Handling request and calling the next handler
    When Vert.x-Web decides to route a request to a matching route, it calls the handler of the route passing in an instance of RoutingContext. A route can have different handlers, that you can append using 
    handler

    If you don’t end the response in your handler, you should call 
    next
    so another matching route can handle the request (if any.

    You don’t have to call 
    next
    before the handler has finished executing. You can do this some time later, if you want

    In the above example route1 is written to the response, then 5 seconds later route2 is written to the response, then 5 seconds later route3 is written to the response and the response is ended.

    Note, all this happens without any thread blocking.

## Auth
#### JWT AUTH
##### What is JSON Web Token?
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA or ECDSA.

Although JWTs can be encrypted to also provide secrecy between parties, we will focus on signed tokens. Signed tokens can verify the integrity of the claims contained within it, while encrypted tokens hide those claims from other parties. When tokens are signed using public/private key pairs, the signature also certifies that only the party holding the private key is the one that signed it.

##### When should you use JSON Web Tokens?
Here are some scenarios where JSON Web Tokens are useful:

###### Authorization
 This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. Single Sign On is a feature that widely uses JWT nowadays, because of its small overhead and its ability to be easily used across different domains.

###### Information Exchange
 JSON Web Tokens are a good way of securely transmitting information between parties. Because JWTs can be signed—for example, using public/private key pairs—you can be sure the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn't been tampered with.
