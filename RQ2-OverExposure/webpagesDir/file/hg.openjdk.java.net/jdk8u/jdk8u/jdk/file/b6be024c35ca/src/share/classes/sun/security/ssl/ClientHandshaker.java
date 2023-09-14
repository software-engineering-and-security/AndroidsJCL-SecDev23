<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: b6be024c35ca src/share/classes/sun/security/ssl/ClientHandshaker.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/b6be024c35ca">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/b6be024c35ca">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/b6be024c35ca">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/b6be024c35ca/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/ClientHandshaker.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/b6be024c35ca/src/share/classes/sun/security/ssl/ClientHandshaker.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/b6be024c35ca/src/share/classes/sun/security/ssl/ClientHandshaker.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/b6be024c35ca/src/share/classes/sun/security/ssl/ClientHandshaker.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/b6be024c35ca/src/share/classes/sun/security/ssl/ClientHandshaker.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/b6be024c35ca/src/share/classes/sun/security/ssl/ClientHandshaker.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/ClientHandshaker.java @ 13892:b6be024c35ca</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8234408: Improve TLS session handling
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#98;&#97;&#107;&#104;&#116;&#105;&#110;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 25 Nov 2019 09:50:30 -0800</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/795199aad0e9/src/share/classes/sun/security/ssl/ClientHandshaker.java">795199aad0e9</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1996, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">package sun.security.ssl;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.*;</span><a href="#l28"></a>
<span id="l29">import java.math.BigInteger;</span><a href="#l29"></a>
<span id="l30">import java.security.*;</span><a href="#l30"></a>
<span id="l31">import java.util.*;</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">import java.security.interfaces.ECPublicKey;</span><a href="#l33"></a>
<span id="l34">import java.security.interfaces.RSAPublicKey;</span><a href="#l34"></a>
<span id="l35">import java.security.spec.ECParameterSpec;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">import java.security.cert.X509Certificate;</span><a href="#l37"></a>
<span id="l38">import java.security.cert.CertificateException;</span><a href="#l38"></a>
<span id="l39">import java.security.cert.CertificateParsingException;</span><a href="#l39"></a>
<span id="l40">import javax.security.auth.x500.X500Principal;</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">import javax.crypto.SecretKey;</span><a href="#l42"></a>
<span id="l43">import javax.crypto.spec.SecretKeySpec;</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">import javax.net.ssl.*;</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">import javax.security.auth.Subject;</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">import sun.security.ssl.HandshakeMessage.*;</span><a href="#l49"></a>
<span id="l50">import static sun.security.ssl.CipherSuite.KeyExchange.*;</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">/**</span><a href="#l52"></a>
<span id="l53"> * ClientHandshaker does the protocol handshaking from the point</span><a href="#l53"></a>
<span id="l54"> * of view of a client.  It is driven asychronously by handshake messages</span><a href="#l54"></a>
<span id="l55"> * as delivered by the parent Handshaker class, and also uses</span><a href="#l55"></a>
<span id="l56"> * common functionality (e.g. key generation) that is provided there.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> * @author David Brownell</span><a href="#l58"></a>
<span id="l59"> */</span><a href="#l59"></a>
<span id="l60">final class ClientHandshaker extends Handshaker {</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    // constants for subject alt names of type DNS and IP</span><a href="#l62"></a>
<span id="l63">    private final static int ALTNAME_DNS = 2;</span><a href="#l63"></a>
<span id="l64">    private final static int ALTNAME_IP  = 7;</span><a href="#l64"></a>
<span id="l65"></span><a href="#l65"></a>
<span id="l66">    // the server's public key from its certificate.</span><a href="#l66"></a>
<span id="l67">    private PublicKey serverKey;</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    // the server's ephemeral public key from the server key exchange message</span><a href="#l69"></a>
<span id="l70">    // for ECDHE/ECDH_anon and RSA_EXPORT.</span><a href="#l70"></a>
<span id="l71">    private PublicKey ephemeralServerKey;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    // server's ephemeral public value for DHE/DH_anon key exchanges</span><a href="#l73"></a>
<span id="l74">    private BigInteger          serverDH;</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    private DHCrypt             dh;</span><a href="#l76"></a>
<span id="l77"></span><a href="#l77"></a>
<span id="l78">    private ECDHCrypt ecdh;</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    private CertificateRequest  certRequest;</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">    private boolean serverKeyExchangeReceived;</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">    /*</span><a href="#l84"></a>
<span id="l85">     * The RSA PreMasterSecret needs to know the version of</span><a href="#l85"></a>
<span id="l86">     * ClientHello that was used on this handshake.  This represents</span><a href="#l86"></a>
<span id="l87">     * the &quot;max version&quot; this client is supporting.  In the</span><a href="#l87"></a>
<span id="l88">     * case of an initial handshake, it's the max version enabled,</span><a href="#l88"></a>
<span id="l89">     * but in the case of a resumption attempt, it's the version</span><a href="#l89"></a>
<span id="l90">     * of the session we're trying to resume.</span><a href="#l90"></a>
<span id="l91">     */</span><a href="#l91"></a>
<span id="l92">    private ProtocolVersion maxProtocolVersion;</span><a href="#l92"></a>
<span id="l93"></span><a href="#l93"></a>
<span id="l94">    // To switch off the SNI extension.</span><a href="#l94"></a>
<span id="l95">    private final static boolean enableSNIExtension =</span><a href="#l95"></a>
<span id="l96">            Debug.getBooleanProperty(&quot;jsse.enableSNIExtension&quot;, true);</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    /*</span><a href="#l98"></a>
<span id="l99">     * Allow unsafe server certificate change?</span><a href="#l99"></a>
<span id="l100">     *</span><a href="#l100"></a>
<span id="l101">     * Server certificate change during SSL/TLS renegotiation may be considered</span><a href="#l101"></a>
<span id="l102">     * unsafe, as described in the Triple Handshake attacks:</span><a href="#l102"></a>
<span id="l103">     *</span><a href="#l103"></a>
<span id="l104">     *     https://secure-resumption.com/tlsauth.pdf</span><a href="#l104"></a>
<span id="l105">     *</span><a href="#l105"></a>
<span id="l106">     * Endpoint identification (See</span><a href="#l106"></a>
<span id="l107">     * SSLParameters.getEndpointIdentificationAlgorithm()) is a pretty nice</span><a href="#l107"></a>
<span id="l108">     * guarantee that the server certificate change in renegotiation is legal.</span><a href="#l108"></a>
<span id="l109">     * However, endpoing identification is only enabled for HTTPS and LDAP</span><a href="#l109"></a>
<span id="l110">     * over SSL/TLS by default.  It is not enough to protect SSL/TLS</span><a href="#l110"></a>
<span id="l111">     * connections other than HTTPS and LDAP.</span><a href="#l111"></a>
<span id="l112">     *</span><a href="#l112"></a>
<span id="l113">     * The renegotiation indication extension (See RFC 5764) is a pretty</span><a href="#l113"></a>
<span id="l114">     * strong guarantee that the endpoints on both client and server sides</span><a href="#l114"></a>
<span id="l115">     * are identical on the same connection.  However, the Triple Handshake</span><a href="#l115"></a>
<span id="l116">     * attacks can bypass this guarantee if there is a session-resumption</span><a href="#l116"></a>
<span id="l117">     * handshake between the initial full handshake and the renegotiation</span><a href="#l117"></a>
<span id="l118">     * full handshake.</span><a href="#l118"></a>
<span id="l119">     *</span><a href="#l119"></a>
<span id="l120">     * Server certificate change may be unsafe and should be restricted if</span><a href="#l120"></a>
<span id="l121">     * endpoint identification is not enabled and the previous handshake is</span><a href="#l121"></a>
<span id="l122">     * a session-resumption abbreviated initial handshake, unless the</span><a href="#l122"></a>
<span id="l123">     * identities represented by both certificates can be regraded as the</span><a href="#l123"></a>
<span id="l124">     * same (See isIdentityEquivalent()).</span><a href="#l124"></a>
<span id="l125">     *</span><a href="#l125"></a>
<span id="l126">     * Considering the compatibility impact and the actual requirements to</span><a href="#l126"></a>
<span id="l127">     * support server certificate change in practice, the system property,</span><a href="#l127"></a>
<span id="l128">     * jdk.tls.allowUnsafeServerCertChange, is used to define whether unsafe</span><a href="#l128"></a>
<span id="l129">     * server certificate change in renegotiation is allowed or not.  The</span><a href="#l129"></a>
<span id="l130">     * default value of the system property is &quot;false&quot;.  To mitigate the</span><a href="#l130"></a>
<span id="l131">     * compactibility impact, applications may want to set the system</span><a href="#l131"></a>
<span id="l132">     * property to &quot;true&quot; at their own risk.</span><a href="#l132"></a>
<span id="l133">     *</span><a href="#l133"></a>
<span id="l134">     * If the value of the system property is &quot;false&quot;, server certificate</span><a href="#l134"></a>
<span id="l135">     * change in renegotiation after a session-resumption abbreviated initial</span><a href="#l135"></a>
<span id="l136">     * handshake is restricted (See isIdentityEquivalent()).</span><a href="#l136"></a>
<span id="l137">     *</span><a href="#l137"></a>
<span id="l138">     * If the system property is set to &quot;true&quot; explicitly, the restriction on</span><a href="#l138"></a>
<span id="l139">     * server certificate change in renegotiation is disabled.</span><a href="#l139"></a>
<span id="l140">     */</span><a href="#l140"></a>
<span id="l141">    private final static boolean allowUnsafeServerCertChange =</span><a href="#l141"></a>
<span id="l142">        Debug.getBooleanProperty(&quot;jdk.tls.allowUnsafeServerCertChange&quot;, false);</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">    // Whether an ALPN extension was sent in the ClientHello</span><a href="#l144"></a>
<span id="l145">    private boolean alpnActive = false;</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">    private List&lt;SNIServerName&gt; requestedServerNames =</span><a href="#l147"></a>
<span id="l148">            Collections.&lt;SNIServerName&gt;emptyList();</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">    private boolean serverNamesAccepted = false;</span><a href="#l150"></a>
<span id="l151"></span><a href="#l151"></a>
<span id="l152">    /*</span><a href="#l152"></a>
<span id="l153">     * the reserved server certificate chain in previous handshaking</span><a href="#l153"></a>
<span id="l154">     *</span><a href="#l154"></a>
<span id="l155">     * The server certificate chain is only reserved if the previous</span><a href="#l155"></a>
<span id="l156">     * handshake is a session-resumption abbreviated initial handshake.</span><a href="#l156"></a>
<span id="l157">     */</span><a href="#l157"></a>
<span id="l158">    private X509Certificate[] reservedServerCerts = null;</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">    /*</span><a href="#l160"></a>
<span id="l161">     * Constructors</span><a href="#l161"></a>
<span id="l162">     */</span><a href="#l162"></a>
<span id="l163">    ClientHandshaker(SSLSocketImpl socket, SSLContextImpl context,</span><a href="#l163"></a>
<span id="l164">            ProtocolList enabledProtocols,</span><a href="#l164"></a>
<span id="l165">            ProtocolVersion activeProtocolVersion,</span><a href="#l165"></a>
<span id="l166">            boolean isInitialHandshake, boolean secureRenegotiation,</span><a href="#l166"></a>
<span id="l167">            byte[] clientVerifyData, byte[] serverVerifyData) {</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">        super(socket, context, enabledProtocols, true, true,</span><a href="#l169"></a>
<span id="l170">            activeProtocolVersion, isInitialHandshake, secureRenegotiation,</span><a href="#l170"></a>
<span id="l171">            clientVerifyData, serverVerifyData);</span><a href="#l171"></a>
<span id="l172">    }</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">    ClientHandshaker(SSLEngineImpl engine, SSLContextImpl context,</span><a href="#l174"></a>
<span id="l175">            ProtocolList enabledProtocols,</span><a href="#l175"></a>
<span id="l176">            ProtocolVersion activeProtocolVersion,</span><a href="#l176"></a>
<span id="l177">            boolean isInitialHandshake, boolean secureRenegotiation,</span><a href="#l177"></a>
<span id="l178">            byte[] clientVerifyData, byte[] serverVerifyData) {</span><a href="#l178"></a>
<span id="l179"></span><a href="#l179"></a>
<span id="l180">        super(engine, context, enabledProtocols, true, true,</span><a href="#l180"></a>
<span id="l181">            activeProtocolVersion, isInitialHandshake, secureRenegotiation,</span><a href="#l181"></a>
<span id="l182">            clientVerifyData, serverVerifyData);</span><a href="#l182"></a>
<span id="l183">    }</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">    /*</span><a href="#l185"></a>
<span id="l186">     * This routine handles all the client side handshake messages, one at</span><a href="#l186"></a>
<span id="l187">     * a time.  Given the message type (and in some cases the pending cipher</span><a href="#l187"></a>
<span id="l188">     * spec) it parses the type-specific message.  Then it calls a function</span><a href="#l188"></a>
<span id="l189">     * that handles that specific message.</span><a href="#l189"></a>
<span id="l190">     *</span><a href="#l190"></a>
<span id="l191">     * It updates the state machine (need to verify it) as each message</span><a href="#l191"></a>
<span id="l192">     * is processed, and writes responses as needed using the connection</span><a href="#l192"></a>
<span id="l193">     * in the constructor.</span><a href="#l193"></a>
<span id="l194">     */</span><a href="#l194"></a>
<span id="l195">    @Override</span><a href="#l195"></a>
<span id="l196">    void processMessage(byte type, int messageLen) throws IOException {</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">        // check the handshake state</span><a href="#l198"></a>
<span id="l199">        List&lt;Byte&gt; ignoredOptStates = handshakeState.check(type);</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">        switch (type) {</span><a href="#l201"></a>
<span id="l202">        case HandshakeMessage.ht_hello_request:</span><a href="#l202"></a>
<span id="l203">            HelloRequest helloRequest = new HelloRequest(input);</span><a href="#l203"></a>
<span id="l204">            handshakeState.update(helloRequest, resumingSession);</span><a href="#l204"></a>
<span id="l205">            this.serverHelloRequest(helloRequest);</span><a href="#l205"></a>
<span id="l206">            break;</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        case HandshakeMessage.ht_server_hello:</span><a href="#l208"></a>
<span id="l209">            ServerHello serverHello = new ServerHello(input, messageLen);</span><a href="#l209"></a>
<span id="l210">            this.serverHello(serverHello);</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">            // This handshake state update needs the resumingSession value</span><a href="#l212"></a>
<span id="l213">            // set by serverHello().</span><a href="#l213"></a>
<span id="l214">            handshakeState.update(serverHello, resumingSession);</span><a href="#l214"></a>
<span id="l215">            break;</span><a href="#l215"></a>
<span id="l216"></span><a href="#l216"></a>
<span id="l217">        case HandshakeMessage.ht_certificate:</span><a href="#l217"></a>
<span id="l218">            if (keyExchange == K_DH_ANON || keyExchange == K_ECDH_ANON</span><a href="#l218"></a>
<span id="l219">                    || keyExchange == K_KRB5 || keyExchange == K_KRB5_EXPORT) {</span><a href="#l219"></a>
<span id="l220">                fatalSE(Alerts.alert_unexpected_message,</span><a href="#l220"></a>
<span id="l221">                    &quot;unexpected server cert chain&quot;);</span><a href="#l221"></a>
<span id="l222">                // NOTREACHED</span><a href="#l222"></a>
<span id="l223">            }</span><a href="#l223"></a>
<span id="l224">            CertificateMsg certificateMsg = new CertificateMsg(input);</span><a href="#l224"></a>
<span id="l225">            handshakeState.update(certificateMsg, resumingSession);</span><a href="#l225"></a>
<span id="l226">            this.serverCertificate(certificateMsg);</span><a href="#l226"></a>
<span id="l227">            serverKey =</span><a href="#l227"></a>
<span id="l228">                session.getPeerCertificates()[0].getPublicKey();</span><a href="#l228"></a>
<span id="l229">            break;</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">        case HandshakeMessage.ht_server_key_exchange:</span><a href="#l231"></a>
<span id="l232">            serverKeyExchangeReceived = true;</span><a href="#l232"></a>
<span id="l233">            switch (keyExchange) {</span><a href="#l233"></a>
<span id="l234">            case K_RSA_EXPORT:</span><a href="#l234"></a>
<span id="l235">                /**</span><a href="#l235"></a>
<span id="l236">                 * The server key exchange message is sent by the server only</span><a href="#l236"></a>
<span id="l237">                 * when the server certificate message does not contain the</span><a href="#l237"></a>
<span id="l238">                 * proper amount of data to allow the client to exchange a</span><a href="#l238"></a>
<span id="l239">                 * premaster secret, such as when RSA_EXPORT is used and the</span><a href="#l239"></a>
<span id="l240">                 * public key in the server certificate is longer than 512 bits.</span><a href="#l240"></a>
<span id="l241">                 */</span><a href="#l241"></a>
<span id="l242">                if (serverKey == null) {</span><a href="#l242"></a>
<span id="l243">                    throw new SSLProtocolException</span><a href="#l243"></a>
<span id="l244">                        (&quot;Server did not send certificate message&quot;);</span><a href="#l244"></a>
<span id="l245">                }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">                if (!(serverKey instanceof RSAPublicKey)) {</span><a href="#l247"></a>
<span id="l248">                    throw new SSLProtocolException(&quot;Protocol violation:&quot; +</span><a href="#l248"></a>
<span id="l249">                        &quot; the certificate type must be appropriate for the&quot; +</span><a href="#l249"></a>
<span id="l250">                        &quot; selected cipher suite's key exchange algorithm&quot;);</span><a href="#l250"></a>
<span id="l251">                }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">                if (JsseJce.getRSAKeyLength(serverKey) &lt;= 512) {</span><a href="#l253"></a>
<span id="l254">                    throw new SSLProtocolException(&quot;Protocol violation:&quot; +</span><a href="#l254"></a>
<span id="l255">                        &quot; server sent a server key exchange message for&quot; +</span><a href="#l255"></a>
<span id="l256">                        &quot; key exchange &quot; + keyExchange +</span><a href="#l256"></a>
<span id="l257">                        &quot; when the public key in the server certificate&quot; +</span><a href="#l257"></a>
<span id="l258">                        &quot; is less than or equal to 512 bits in length&quot;);</span><a href="#l258"></a>
<span id="l259">                }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">                try {</span><a href="#l261"></a>
<span id="l262">                    RSA_ServerKeyExchange rsaSrvKeyExchange =</span><a href="#l262"></a>
<span id="l263">                                    new RSA_ServerKeyExchange(input);</span><a href="#l263"></a>
<span id="l264">                    handshakeState.update(rsaSrvKeyExchange, resumingSession);</span><a href="#l264"></a>
<span id="l265">                    this.serverKeyExchange(rsaSrvKeyExchange);</span><a href="#l265"></a>
<span id="l266">                } catch (GeneralSecurityException e) {</span><a href="#l266"></a>
<span id="l267">                    throwSSLException(&quot;Server key&quot;, e);</span><a href="#l267"></a>
<span id="l268">                }</span><a href="#l268"></a>
<span id="l269">                break;</span><a href="#l269"></a>
<span id="l270">            case K_DH_ANON:</span><a href="#l270"></a>
<span id="l271">                try {</span><a href="#l271"></a>
<span id="l272">                    DH_ServerKeyExchange dhSrvKeyExchange =</span><a href="#l272"></a>
<span id="l273">                            new DH_ServerKeyExchange(input, protocolVersion);</span><a href="#l273"></a>
<span id="l274">                    handshakeState.update(dhSrvKeyExchange, resumingSession);</span><a href="#l274"></a>
<span id="l275">                    this.serverKeyExchange(dhSrvKeyExchange);</span><a href="#l275"></a>
<span id="l276">                } catch (GeneralSecurityException e) {</span><a href="#l276"></a>
<span id="l277">                    throwSSLException(&quot;Server key&quot;, e);</span><a href="#l277"></a>
<span id="l278">                }</span><a href="#l278"></a>
<span id="l279">                break;</span><a href="#l279"></a>
<span id="l280">            case K_DHE_DSS:</span><a href="#l280"></a>
<span id="l281">            case K_DHE_RSA:</span><a href="#l281"></a>
<span id="l282">                try {</span><a href="#l282"></a>
<span id="l283">                    DH_ServerKeyExchange dhSrvKeyExchange =</span><a href="#l283"></a>
<span id="l284">                        new DH_ServerKeyExchange(</span><a href="#l284"></a>
<span id="l285">                        input, serverKey,</span><a href="#l285"></a>
<span id="l286">                        clnt_random.random_bytes, svr_random.random_bytes,</span><a href="#l286"></a>
<span id="l287">                        messageLen,</span><a href="#l287"></a>
<span id="l288">                            getLocalSupportedSignAlgs(), protocolVersion);</span><a href="#l288"></a>
<span id="l289">                    handshakeState.update(dhSrvKeyExchange, resumingSession);</span><a href="#l289"></a>
<span id="l290">                    this.serverKeyExchange(dhSrvKeyExchange);</span><a href="#l290"></a>
<span id="l291">                } catch (GeneralSecurityException e) {</span><a href="#l291"></a>
<span id="l292">                    throwSSLException(&quot;Server key&quot;, e);</span><a href="#l292"></a>
<span id="l293">                }</span><a href="#l293"></a>
<span id="l294">                break;</span><a href="#l294"></a>
<span id="l295">            case K_ECDHE_ECDSA:</span><a href="#l295"></a>
<span id="l296">            case K_ECDHE_RSA:</span><a href="#l296"></a>
<span id="l297">            case K_ECDH_ANON:</span><a href="#l297"></a>
<span id="l298">                try {</span><a href="#l298"></a>
<span id="l299">                    ECDH_ServerKeyExchange ecdhSrvKeyExchange =</span><a href="#l299"></a>
<span id="l300">                        new ECDH_ServerKeyExchange</span><a href="#l300"></a>
<span id="l301">                        (input, serverKey, clnt_random.random_bytes,</span><a href="#l301"></a>
<span id="l302">                        svr_random.random_bytes,</span><a href="#l302"></a>
<span id="l303">                            getLocalSupportedSignAlgs(), protocolVersion);</span><a href="#l303"></a>
<span id="l304">                    handshakeState.update(ecdhSrvKeyExchange, resumingSession);</span><a href="#l304"></a>
<span id="l305">                    this.serverKeyExchange(ecdhSrvKeyExchange);</span><a href="#l305"></a>
<span id="l306">                } catch (GeneralSecurityException e) {</span><a href="#l306"></a>
<span id="l307">                    throwSSLException(&quot;Server key&quot;, e);</span><a href="#l307"></a>
<span id="l308">                }</span><a href="#l308"></a>
<span id="l309">                break;</span><a href="#l309"></a>
<span id="l310">            case K_RSA:</span><a href="#l310"></a>
<span id="l311">            case K_DH_RSA:</span><a href="#l311"></a>
<span id="l312">            case K_DH_DSS:</span><a href="#l312"></a>
<span id="l313">            case K_ECDH_ECDSA:</span><a href="#l313"></a>
<span id="l314">            case K_ECDH_RSA:</span><a href="#l314"></a>
<span id="l315">                throw new SSLProtocolException(</span><a href="#l315"></a>
<span id="l316">                    &quot;Protocol violation: server sent a server key exchange&quot;</span><a href="#l316"></a>
<span id="l317">                    + &quot; message for key exchange &quot; + keyExchange);</span><a href="#l317"></a>
<span id="l318">            case K_KRB5:</span><a href="#l318"></a>
<span id="l319">            case K_KRB5_EXPORT:</span><a href="#l319"></a>
<span id="l320">                throw new SSLProtocolException(</span><a href="#l320"></a>
<span id="l321">                    &quot;unexpected receipt of server key exchange algorithm&quot;);</span><a href="#l321"></a>
<span id="l322">            default:</span><a href="#l322"></a>
<span id="l323">                throw new SSLProtocolException(</span><a href="#l323"></a>
<span id="l324">                    &quot;unsupported key exchange algorithm = &quot;</span><a href="#l324"></a>
<span id="l325">                    + keyExchange);</span><a href="#l325"></a>
<span id="l326">            }</span><a href="#l326"></a>
<span id="l327">            break;</span><a href="#l327"></a>
<span id="l328"></span><a href="#l328"></a>
<span id="l329">        case HandshakeMessage.ht_certificate_request:</span><a href="#l329"></a>
<span id="l330">            // save for later, it's handled by serverHelloDone</span><a href="#l330"></a>
<span id="l331">            if ((keyExchange == K_DH_ANON) || (keyExchange == K_ECDH_ANON)) {</span><a href="#l331"></a>
<span id="l332">                throw new SSLHandshakeException(</span><a href="#l332"></a>
<span id="l333">                    &quot;Client authentication requested for &quot;+</span><a href="#l333"></a>
<span id="l334">                    &quot;anonymous cipher suite.&quot;);</span><a href="#l334"></a>
<span id="l335">            } else if (keyExchange == K_KRB5 || keyExchange == K_KRB5_EXPORT) {</span><a href="#l335"></a>
<span id="l336">                throw new SSLHandshakeException(</span><a href="#l336"></a>
<span id="l337">                    &quot;Client certificate requested for &quot;+</span><a href="#l337"></a>
<span id="l338">                    &quot;kerberos cipher suite.&quot;);</span><a href="#l338"></a>
<span id="l339">            }</span><a href="#l339"></a>
<span id="l340">            certRequest = new CertificateRequest(input, protocolVersion);</span><a href="#l340"></a>
<span id="l341">            if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l341"></a>
<span id="l342">                certRequest.print(System.out);</span><a href="#l342"></a>
<span id="l343">            }</span><a href="#l343"></a>
<span id="l344">            handshakeState.update(certRequest, resumingSession);</span><a href="#l344"></a>
<span id="l345"></span><a href="#l345"></a>
<span id="l346">            if (protocolVersion.v &gt;= ProtocolVersion.TLS12.v) {</span><a href="#l346"></a>
<span id="l347">                Collection&lt;SignatureAndHashAlgorithm&gt; peerSignAlgs =</span><a href="#l347"></a>
<span id="l348">                                        certRequest.getSignAlgorithms();</span><a href="#l348"></a>
<span id="l349">                if (peerSignAlgs == null || peerSignAlgs.isEmpty()) {</span><a href="#l349"></a>
<span id="l350">                    throw new SSLHandshakeException(</span><a href="#l350"></a>
<span id="l351">                        &quot;No peer supported signature algorithms&quot;);</span><a href="#l351"></a>
<span id="l352">                }</span><a href="#l352"></a>
<span id="l353"></span><a href="#l353"></a>
<span id="l354">                Collection&lt;SignatureAndHashAlgorithm&gt; supportedPeerSignAlgs =</span><a href="#l354"></a>
<span id="l355">                    SignatureAndHashAlgorithm.getSupportedAlgorithms(</span><a href="#l355"></a>
<span id="l356">                            algorithmConstraints, peerSignAlgs);</span><a href="#l356"></a>
<span id="l357">                if (supportedPeerSignAlgs.isEmpty()) {</span><a href="#l357"></a>
<span id="l358">                    throw new SSLHandshakeException(</span><a href="#l358"></a>
<span id="l359">                        &quot;No supported signature and hash algorithm in common&quot;);</span><a href="#l359"></a>
<span id="l360">                }</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362">                setPeerSupportedSignAlgs(supportedPeerSignAlgs);</span><a href="#l362"></a>
<span id="l363">                session.setPeerSupportedSignatureAlgorithms(</span><a href="#l363"></a>
<span id="l364">                                                supportedPeerSignAlgs);</span><a href="#l364"></a>
<span id="l365">            }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">            break;</span><a href="#l367"></a>
<span id="l368"></span><a href="#l368"></a>
<span id="l369">        case HandshakeMessage.ht_server_hello_done:</span><a href="#l369"></a>
<span id="l370">            ServerHelloDone serverHelloDone = new ServerHelloDone(input);</span><a href="#l370"></a>
<span id="l371">            handshakeState.update(serverHelloDone, resumingSession);</span><a href="#l371"></a>
<span id="l372">            this.serverHelloDone(serverHelloDone);</span><a href="#l372"></a>
<span id="l373">            break;</span><a href="#l373"></a>
<span id="l374"></span><a href="#l374"></a>
<span id="l375">        case HandshakeMessage.ht_finished:</span><a href="#l375"></a>
<span id="l376">            Finished serverFinished =</span><a href="#l376"></a>
<span id="l377">                    new Finished(protocolVersion, input, cipherSuite);</span><a href="#l377"></a>
<span id="l378">            handshakeState.update(serverFinished, resumingSession);</span><a href="#l378"></a>
<span id="l379">            this.serverFinished(serverFinished);</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">            break;</span><a href="#l381"></a>
<span id="l382"></span><a href="#l382"></a>
<span id="l383">        default:</span><a href="#l383"></a>
<span id="l384">            throw new SSLProtocolException(</span><a href="#l384"></a>
<span id="l385">                &quot;Illegal client handshake msg, &quot; + type);</span><a href="#l385"></a>
<span id="l386">        }</span><a href="#l386"></a>
<span id="l387">    }</span><a href="#l387"></a>
<span id="l388"></span><a href="#l388"></a>
<span id="l389">    /*</span><a href="#l389"></a>
<span id="l390">     * Used by the server to kickstart negotiations -- this requests a</span><a href="#l390"></a>
<span id="l391">     * &quot;client hello&quot; to renegotiate current cipher specs (e.g. maybe lots</span><a href="#l391"></a>
<span id="l392">     * of data has been encrypted with the same keys, or the server needs</span><a href="#l392"></a>
<span id="l393">     * the client to present a certificate).</span><a href="#l393"></a>
<span id="l394">     */</span><a href="#l394"></a>
<span id="l395">    private void serverHelloRequest(HelloRequest mesg) throws IOException {</span><a href="#l395"></a>
<span id="l396">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l396"></a>
<span id="l397">            mesg.print(System.out);</span><a href="#l397"></a>
<span id="l398">        }</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">        //</span><a href="#l400"></a>
<span id="l401">        // Could be (e.g. at connection setup) that we already</span><a href="#l401"></a>
<span id="l402">        // sent the &quot;client hello&quot; but the server's not seen it.</span><a href="#l402"></a>
<span id="l403">        //</span><a href="#l403"></a>
<span id="l404">        if (!clientHelloDelivered) {</span><a href="#l404"></a>
<span id="l405">            if (!secureRenegotiation &amp;&amp; !allowUnsafeRenegotiation) {</span><a href="#l405"></a>
<span id="l406">                // renegotiation is not allowed.</span><a href="#l406"></a>
<span id="l407">                if (activeProtocolVersion.v &gt;= ProtocolVersion.TLS10.v) {</span><a href="#l407"></a>
<span id="l408">                    // response with a no_renegotiation warning,</span><a href="#l408"></a>
<span id="l409">                    warningSE(Alerts.alert_no_renegotiation);</span><a href="#l409"></a>
<span id="l410"></span><a href="#l410"></a>
<span id="l411">                    // invalidate the handshake so that the caller can</span><a href="#l411"></a>
<span id="l412">                    // dispose this object.</span><a href="#l412"></a>
<span id="l413">                    invalidated = true;</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">                    // If there is still unread block in the handshake</span><a href="#l415"></a>
<span id="l416">                    // input stream, it would be truncated with the disposal</span><a href="#l416"></a>
<span id="l417">                    // and the next handshake message will become incomplete.</span><a href="#l417"></a>
<span id="l418">                    //</span><a href="#l418"></a>
<span id="l419">                    // However, according to SSL/TLS specifications, no more</span><a href="#l419"></a>
<span id="l420">                    // handshake message should immediately follow ClientHello</span><a href="#l420"></a>
<span id="l421">                    // or HelloRequest. So just let it be.</span><a href="#l421"></a>
<span id="l422">                } else {</span><a href="#l422"></a>
<span id="l423">                    // For SSLv3, send the handshake_failure fatal error.</span><a href="#l423"></a>
<span id="l424">                    // Note that SSLv3 does not define a no_renegotiation</span><a href="#l424"></a>
<span id="l425">                    // alert like TLSv1. However we cannot ignore the message</span><a href="#l425"></a>
<span id="l426">                    // simply, otherwise the other side was waiting for a</span><a href="#l426"></a>
<span id="l427">                    // response that would never come.</span><a href="#l427"></a>
<span id="l428">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l428"></a>
<span id="l429">                        &quot;Renegotiation is not allowed&quot;);</span><a href="#l429"></a>
<span id="l430">                }</span><a href="#l430"></a>
<span id="l431">            } else {</span><a href="#l431"></a>
<span id="l432">                if (!secureRenegotiation) {</span><a href="#l432"></a>
<span id="l433">                    if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l433"></a>
<span id="l434">                        System.out.println(</span><a href="#l434"></a>
<span id="l435">                            &quot;Warning: continue with insecure renegotiation&quot;);</span><a href="#l435"></a>
<span id="l436">                    }</span><a href="#l436"></a>
<span id="l437">                }</span><a href="#l437"></a>
<span id="l438">                kickstart();</span><a href="#l438"></a>
<span id="l439">            }</span><a href="#l439"></a>
<span id="l440">        }</span><a href="#l440"></a>
<span id="l441">    }</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443"></span><a href="#l443"></a>
<span id="l444">    /*</span><a href="#l444"></a>
<span id="l445">     * Server chooses session parameters given options created by the</span><a href="#l445"></a>
<span id="l446">     * client -- basically, cipher options, session id, and someday a</span><a href="#l446"></a>
<span id="l447">     * set of compression options.</span><a href="#l447"></a>
<span id="l448">     *</span><a href="#l448"></a>
<span id="l449">     * There are two branches of the state machine, decided by the</span><a href="#l449"></a>
<span id="l450">     * details of this message.  One is the &quot;fast&quot; handshake, where we</span><a href="#l450"></a>
<span id="l451">     * can resume the pre-existing session we asked resume.  The other</span><a href="#l451"></a>
<span id="l452">     * is a more expensive &quot;full&quot; handshake, with key exchange and</span><a href="#l452"></a>
<span id="l453">     * probably authentication getting done.</span><a href="#l453"></a>
<span id="l454">     */</span><a href="#l454"></a>
<span id="l455">    private void serverHello(ServerHello mesg) throws IOException {</span><a href="#l455"></a>
<span id="l456">        serverKeyExchangeReceived = false;</span><a href="#l456"></a>
<span id="l457">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l457"></a>
<span id="l458">            mesg.print(System.out);</span><a href="#l458"></a>
<span id="l459">        }</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">        // check if the server selected protocol version is OK for us</span><a href="#l461"></a>
<span id="l462">        ProtocolVersion mesgVersion = mesg.protocolVersion;</span><a href="#l462"></a>
<span id="l463">        if (!isNegotiable(mesgVersion)) {</span><a href="#l463"></a>
<span id="l464">            throw new SSLHandshakeException(</span><a href="#l464"></a>
<span id="l465">                &quot;Server chose &quot; + mesgVersion +</span><a href="#l465"></a>
<span id="l466">                &quot;, but that protocol version is not enabled or not supported &quot; +</span><a href="#l466"></a>
<span id="l467">                &quot;by the client.&quot;);</span><a href="#l467"></a>
<span id="l468">        }</span><a href="#l468"></a>
<span id="l469"></span><a href="#l469"></a>
<span id="l470">        handshakeHash.protocolDetermined(mesgVersion);</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">        // Set protocolVersion and propagate to SSLSocket and the</span><a href="#l472"></a>
<span id="l473">        // Handshake streams</span><a href="#l473"></a>
<span id="l474">        setVersion(mesgVersion);</span><a href="#l474"></a>
<span id="l475"></span><a href="#l475"></a>
<span id="l476">        // check the &quot;renegotiation_info&quot; extension</span><a href="#l476"></a>
<span id="l477">        RenegotiationInfoExtension serverHelloRI = (RenegotiationInfoExtension)</span><a href="#l477"></a>
<span id="l478">                    mesg.extensions.get(ExtensionType.EXT_RENEGOTIATION_INFO);</span><a href="#l478"></a>
<span id="l479">        if (serverHelloRI != null) {</span><a href="#l479"></a>
<span id="l480">            if (isInitialHandshake) {</span><a href="#l480"></a>
<span id="l481">                // verify the length of the &quot;renegotiated_connection&quot; field</span><a href="#l481"></a>
<span id="l482">                if (!serverHelloRI.isEmpty()) {</span><a href="#l482"></a>
<span id="l483">                    // abort the handshake with a fatal handshake_failure alert</span><a href="#l483"></a>
<span id="l484">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l484"></a>
<span id="l485">                        &quot;The renegotiation_info field is not empty&quot;);</span><a href="#l485"></a>
<span id="l486">                }</span><a href="#l486"></a>
<span id="l487"></span><a href="#l487"></a>
<span id="l488">                secureRenegotiation = true;</span><a href="#l488"></a>
<span id="l489">            } else {</span><a href="#l489"></a>
<span id="l490">                // For a legacy renegotiation, the client MUST verify that</span><a href="#l490"></a>
<span id="l491">                // it does not contain the &quot;renegotiation_info&quot; extension.</span><a href="#l491"></a>
<span id="l492">                if (!secureRenegotiation) {</span><a href="#l492"></a>
<span id="l493">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l493"></a>
<span id="l494">                        &quot;Unexpected renegotiation indication extension&quot;);</span><a href="#l494"></a>
<span id="l495">                }</span><a href="#l495"></a>
<span id="l496"></span><a href="#l496"></a>
<span id="l497">                // verify the client_verify_data and server_verify_data values</span><a href="#l497"></a>
<span id="l498">                byte[] verifyData =</span><a href="#l498"></a>
<span id="l499">                    new byte[clientVerifyData.length + serverVerifyData.length];</span><a href="#l499"></a>
<span id="l500">                System.arraycopy(clientVerifyData, 0, verifyData,</span><a href="#l500"></a>
<span id="l501">                        0, clientVerifyData.length);</span><a href="#l501"></a>
<span id="l502">                System.arraycopy(serverVerifyData, 0, verifyData,</span><a href="#l502"></a>
<span id="l503">                        clientVerifyData.length, serverVerifyData.length);</span><a href="#l503"></a>
<span id="l504">                if (!MessageDigest.isEqual(verifyData,</span><a href="#l504"></a>
<span id="l505">                                serverHelloRI.getRenegotiatedConnection())) {</span><a href="#l505"></a>
<span id="l506">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l506"></a>
<span id="l507">                        &quot;Incorrect verify data in ServerHello &quot; +</span><a href="#l507"></a>
<span id="l508">                        &quot;renegotiation_info message&quot;);</span><a href="#l508"></a>
<span id="l509">                }</span><a href="#l509"></a>
<span id="l510">            }</span><a href="#l510"></a>
<span id="l511">        } else {</span><a href="#l511"></a>
<span id="l512">            // no renegotiation indication extension</span><a href="#l512"></a>
<span id="l513">            if (isInitialHandshake) {</span><a href="#l513"></a>
<span id="l514">                if (!allowLegacyHelloMessages) {</span><a href="#l514"></a>
<span id="l515">                    // abort the handshake with a fatal handshake_failure alert</span><a href="#l515"></a>
<span id="l516">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l516"></a>
<span id="l517">                        &quot;Failed to negotiate the use of secure renegotiation&quot;);</span><a href="#l517"></a>
<span id="l518">                }</span><a href="#l518"></a>
<span id="l519"></span><a href="#l519"></a>
<span id="l520">                secureRenegotiation = false;</span><a href="#l520"></a>
<span id="l521">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l521"></a>
<span id="l522">                    System.out.println(&quot;Warning: No renegotiation &quot; +</span><a href="#l522"></a>
<span id="l523">                                    &quot;indication extension in ServerHello&quot;);</span><a href="#l523"></a>
<span id="l524">                }</span><a href="#l524"></a>
<span id="l525">            } else {</span><a href="#l525"></a>
<span id="l526">                // For a secure renegotiation, the client must abort the</span><a href="#l526"></a>
<span id="l527">                // handshake if no &quot;renegotiation_info&quot; extension is present.</span><a href="#l527"></a>
<span id="l528">                if (secureRenegotiation) {</span><a href="#l528"></a>
<span id="l529">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l529"></a>
<span id="l530">                        &quot;No renegotiation indication extension&quot;);</span><a href="#l530"></a>
<span id="l531">                }</span><a href="#l531"></a>
<span id="l532"></span><a href="#l532"></a>
<span id="l533">                // we have already allowed unsafe renegotation before request</span><a href="#l533"></a>
<span id="l534">                // the renegotiation.</span><a href="#l534"></a>
<span id="l535">            }</span><a href="#l535"></a>
<span id="l536">        }</span><a href="#l536"></a>
<span id="l537"></span><a href="#l537"></a>
<span id="l538">        //</span><a href="#l538"></a>
<span id="l539">        // Save server nonce, we always use it to compute connection</span><a href="#l539"></a>
<span id="l540">        // keys and it's also used to create the master secret if we're</span><a href="#l540"></a>
<span id="l541">        // creating a new session (i.e. in the full handshake).</span><a href="#l541"></a>
<span id="l542">        //</span><a href="#l542"></a>
<span id="l543">        svr_random = mesg.svr_random;</span><a href="#l543"></a>
<span id="l544"></span><a href="#l544"></a>
<span id="l545">        if (isNegotiable(mesg.cipherSuite) == false) {</span><a href="#l545"></a>
<span id="l546">            fatalSE(Alerts.alert_illegal_parameter,</span><a href="#l546"></a>
<span id="l547">                &quot;Server selected improper ciphersuite &quot; + mesg.cipherSuite);</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">        setCipherSuite(mesg.cipherSuite);</span><a href="#l550"></a>
<span id="l551">        if (protocolVersion.v &gt;= ProtocolVersion.TLS12.v) {</span><a href="#l551"></a>
<span id="l552">            handshakeHash.setFinishedAlg(cipherSuite.prfAlg.getPRFHashAlg());</span><a href="#l552"></a>
<span id="l553">        }</span><a href="#l553"></a>
<span id="l554"></span><a href="#l554"></a>
<span id="l555">        if (mesg.compression_method != 0) {</span><a href="#l555"></a>
<span id="l556">            fatalSE(Alerts.alert_illegal_parameter,</span><a href="#l556"></a>
<span id="l557">                &quot;compression type not supported, &quot;</span><a href="#l557"></a>
<span id="l558">                + mesg.compression_method);</span><a href="#l558"></a>
<span id="l559">            // NOTREACHED</span><a href="#l559"></a>
<span id="l560">        }</span><a href="#l560"></a>
<span id="l561"></span><a href="#l561"></a>
<span id="l562">        // so far so good, let's look at the session</span><a href="#l562"></a>
<span id="l563">        if (session != null) {</span><a href="#l563"></a>
<span id="l564">            // we tried to resume, let's see what the server decided</span><a href="#l564"></a>
<span id="l565">            if (session.getSessionId().equals(mesg.sessionId)) {</span><a href="#l565"></a>
<span id="l566">                // server resumed the session, let's make sure everything</span><a href="#l566"></a>
<span id="l567">                // checks out</span><a href="#l567"></a>
<span id="l568"></span><a href="#l568"></a>
<span id="l569">                // Verify that the session ciphers are unchanged.</span><a href="#l569"></a>
<span id="l570">                CipherSuite sessionSuite = session.getSuite();</span><a href="#l570"></a>
<span id="l571">                if (cipherSuite != sessionSuite) {</span><a href="#l571"></a>
<span id="l572">                    throw new SSLProtocolException</span><a href="#l572"></a>
<span id="l573">                        (&quot;Server returned wrong cipher suite for session&quot;);</span><a href="#l573"></a>
<span id="l574">                }</span><a href="#l574"></a>
<span id="l575"></span><a href="#l575"></a>
<span id="l576">                // verify protocol version match</span><a href="#l576"></a>
<span id="l577">                ProtocolVersion sessionVersion = session.getProtocolVersion();</span><a href="#l577"></a>
<span id="l578">                if (protocolVersion != sessionVersion) {</span><a href="#l578"></a>
<span id="l579">                    throw new SSLProtocolException</span><a href="#l579"></a>
<span id="l580">                        (&quot;Server resumed session with wrong protocol version&quot;);</span><a href="#l580"></a>
<span id="l581">                }</span><a href="#l581"></a>
<span id="l582"></span><a href="#l582"></a>
<span id="l583">                // validate subject identity</span><a href="#l583"></a>
<span id="l584">                if (sessionSuite.keyExchange == K_KRB5 ||</span><a href="#l584"></a>
<span id="l585">                    sessionSuite.keyExchange == K_KRB5_EXPORT) {</span><a href="#l585"></a>
<span id="l586">                    Principal localPrincipal = session.getLocalPrincipal();</span><a href="#l586"></a>
<span id="l587"></span><a href="#l587"></a>
<span id="l588">                    Subject subject = null;</span><a href="#l588"></a>
<span id="l589">                    try {</span><a href="#l589"></a>
<span id="l590">                        subject = AccessController.doPrivileged(</span><a href="#l590"></a>
<span id="l591">                            new PrivilegedExceptionAction&lt;Subject&gt;() {</span><a href="#l591"></a>
<span id="l592">                            @Override</span><a href="#l592"></a>
<span id="l593">                            public Subject run() throws Exception {</span><a href="#l593"></a>
<span id="l594">                                return Krb5Helper.getClientSubject(getAccSE());</span><a href="#l594"></a>
<span id="l595">                            }});</span><a href="#l595"></a>
<span id="l596">                    } catch (PrivilegedActionException e) {</span><a href="#l596"></a>
<span id="l597">                        subject = null;</span><a href="#l597"></a>
<span id="l598">                        if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l598"></a>
<span id="l599">                            System.out.println(&quot;Attempt to obtain&quot; +</span><a href="#l599"></a>
<span id="l600">                                        &quot; subject failed!&quot;);</span><a href="#l600"></a>
<span id="l601">                        }</span><a href="#l601"></a>
<span id="l602">                    }</span><a href="#l602"></a>
<span id="l603"></span><a href="#l603"></a>
<span id="l604">                    if (subject != null) {</span><a href="#l604"></a>
<span id="l605">                        // Eliminate dependency on KerberosPrincipal</span><a href="#l605"></a>
<span id="l606">                        Set&lt;Principal&gt; principals =</span><a href="#l606"></a>
<span id="l607">                            subject.getPrincipals(Principal.class);</span><a href="#l607"></a>
<span id="l608">                        if (!principals.contains(localPrincipal)) {</span><a href="#l608"></a>
<span id="l609">                            throw new SSLProtocolException(&quot;Server resumed&quot; +</span><a href="#l609"></a>
<span id="l610">                                &quot; session with wrong subject identity&quot;);</span><a href="#l610"></a>
<span id="l611">                        } else {</span><a href="#l611"></a>
<span id="l612">                            if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;))</span><a href="#l612"></a>
<span id="l613">                                System.out.println(&quot;Subject identity is same&quot;);</span><a href="#l613"></a>
<span id="l614">                        }</span><a href="#l614"></a>
<span id="l615">                    } else {</span><a href="#l615"></a>
<span id="l616">                        if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;))</span><a href="#l616"></a>
<span id="l617">                            System.out.println(&quot;Kerberos credentials are not&quot; +</span><a href="#l617"></a>
<span id="l618">                                &quot; present in the current Subject; check if &quot; +</span><a href="#l618"></a>
<span id="l619">                                &quot; javax.security.auth.useSubjectAsCreds&quot; +</span><a href="#l619"></a>
<span id="l620">                                &quot; system property has been set to false&quot;);</span><a href="#l620"></a>
<span id="l621">                        throw new SSLProtocolException</span><a href="#l621"></a>
<span id="l622">                            (&quot;Server resumed session with no subject&quot;);</span><a href="#l622"></a>
<span id="l623">                    }</span><a href="#l623"></a>
<span id="l624">                }</span><a href="#l624"></a>
<span id="l625"></span><a href="#l625"></a>
<span id="l626">                // looks fine; resume it, and update the state machine.</span><a href="#l626"></a>
<span id="l627">                resumingSession = true;</span><a href="#l627"></a>
<span id="l628">                calculateConnectionKeys(session.getMasterSecret());</span><a href="#l628"></a>
<span id="l629">                if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l629"></a>
<span id="l630">                    System.out.println(&quot;%% Server resumed &quot; + session);</span><a href="#l630"></a>
<span id="l631">                }</span><a href="#l631"></a>
<span id="l632">            } else {</span><a href="#l632"></a>
<span id="l633">                // we wanted to resume, but the server refused</span><a href="#l633"></a>
<span id="l634">                //</span><a href="#l634"></a>
<span id="l635">                // Invalidate the session for initial handshake in case</span><a href="#l635"></a>
<span id="l636">                // of reusing next time.</span><a href="#l636"></a>
<span id="l637">                if (isInitialHandshake) {</span><a href="#l637"></a>
<span id="l638">                    session.invalidate();</span><a href="#l638"></a>
<span id="l639">                }</span><a href="#l639"></a>
<span id="l640">                session = null;</span><a href="#l640"></a>
<span id="l641">                if (!enableNewSession) {</span><a href="#l641"></a>
<span id="l642">                    throw new SSLException(&quot;New session creation is disabled&quot;);</span><a href="#l642"></a>
<span id="l643">                }</span><a href="#l643"></a>
<span id="l644">            }</span><a href="#l644"></a>
<span id="l645">        }</span><a href="#l645"></a>
<span id="l646"></span><a href="#l646"></a>
<span id="l647">        // check the &quot;extended_master_secret&quot; extension</span><a href="#l647"></a>
<span id="l648">        ExtendedMasterSecretExtension extendedMasterSecretExt =</span><a href="#l648"></a>
<span id="l649">                (ExtendedMasterSecretExtension)mesg.extensions.get(</span><a href="#l649"></a>
<span id="l650">                        ExtensionType.EXT_EXTENDED_MASTER_SECRET);</span><a href="#l650"></a>
<span id="l651">        if (extendedMasterSecretExt != null) {</span><a href="#l651"></a>
<span id="l652">            // Is it the expected server extension?</span><a href="#l652"></a>
<span id="l653">            if (!useExtendedMasterSecret ||</span><a href="#l653"></a>
<span id="l654">                    !(mesgVersion.v &gt;= ProtocolVersion.TLS10.v) || !requestedToUseEMS) {</span><a href="#l654"></a>
<span id="l655">                fatalSE(Alerts.alert_unsupported_extension,</span><a href="#l655"></a>
<span id="l656">                        &quot;Server sent the extended_master_secret &quot; +</span><a href="#l656"></a>
<span id="l657">                        &quot;extension improperly&quot;);</span><a href="#l657"></a>
<span id="l658">            }</span><a href="#l658"></a>
<span id="l659"></span><a href="#l659"></a>
<span id="l660">            // For abbreviated handshake, if the original session did not use</span><a href="#l660"></a>
<span id="l661">            // the &quot;extended_master_secret&quot; extension but the new ServerHello</span><a href="#l661"></a>
<span id="l662">            // contains the extension, the client MUST abort the handshake.</span><a href="#l662"></a>
<span id="l663">            if (resumingSession &amp;&amp; (session != null) &amp;&amp;</span><a href="#l663"></a>
<span id="l664">                    !session.getUseExtendedMasterSecret()) {</span><a href="#l664"></a>
<span id="l665">                fatalSE(Alerts.alert_unsupported_extension,</span><a href="#l665"></a>
<span id="l666">                        &quot;Server sent an unexpected extended_master_secret &quot; +</span><a href="#l666"></a>
<span id="l667">                        &quot;extension on session resumption&quot;);</span><a href="#l667"></a>
<span id="l668">            }</span><a href="#l668"></a>
<span id="l669">        } else {</span><a href="#l669"></a>
<span id="l670">            if (useExtendedMasterSecret &amp;&amp; !allowLegacyMasterSecret) {</span><a href="#l670"></a>
<span id="l671">                // For full handshake, if a client receives a ServerHello</span><a href="#l671"></a>
<span id="l672">                // without the extension, it SHOULD abort the handshake if</span><a href="#l672"></a>
<span id="l673">                // it does not wish to interoperate with legacy servers.</span><a href="#l673"></a>
<span id="l674">                fatalSE(Alerts.alert_handshake_failure,</span><a href="#l674"></a>
<span id="l675">                    &quot;Extended Master Secret extension is required&quot;);</span><a href="#l675"></a>
<span id="l676">            }</span><a href="#l676"></a>
<span id="l677"></span><a href="#l677"></a>
<span id="l678">            if (resumingSession &amp;&amp; (session != null)) {</span><a href="#l678"></a>
<span id="l679">                if (session.getUseExtendedMasterSecret()) {</span><a href="#l679"></a>
<span id="l680">                    // For abbreviated handshake, if the original session used</span><a href="#l680"></a>
<span id="l681">                    // the &quot;extended_master_secret&quot; extension but the new</span><a href="#l681"></a>
<span id="l682">                    // ServerHello does not contain the extension, the client</span><a href="#l682"></a>
<span id="l683">                    // MUST abort the handshake.</span><a href="#l683"></a>
<span id="l684">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l684"></a>
<span id="l685">                            &quot;Missing Extended Master Secret extension &quot; +</span><a href="#l685"></a>
<span id="l686">                            &quot;on session resumption&quot;);</span><a href="#l686"></a>
<span id="l687">                } else if (useExtendedMasterSecret &amp;&amp; !allowLegacyResumption) {</span><a href="#l687"></a>
<span id="l688">                    // Unlikely, abbreviated handshake should be discarded.</span><a href="#l688"></a>
<span id="l689">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l689"></a>
<span id="l690">                        &quot;Extended Master Secret extension is required&quot;);</span><a href="#l690"></a>
<span id="l691">                }</span><a href="#l691"></a>
<span id="l692">            }</span><a href="#l692"></a>
<span id="l693">        }</span><a href="#l693"></a>
<span id="l694"></span><a href="#l694"></a>
<span id="l695">        // check the ALPN extension</span><a href="#l695"></a>
<span id="l696">        ALPNExtension serverHelloALPN =</span><a href="#l696"></a>
<span id="l697">            (ALPNExtension) mesg.extensions.get(ExtensionType.EXT_ALPN);</span><a href="#l697"></a>
<span id="l698"></span><a href="#l698"></a>
<span id="l699">        if (serverHelloALPN != null) {</span><a href="#l699"></a>
<span id="l700">            // Check whether an ALPN extension was sent in ClientHello message</span><a href="#l700"></a>
<span id="l701">            if (!alpnActive) {</span><a href="#l701"></a>
<span id="l702">                fatalSE(Alerts.alert_unsupported_extension,</span><a href="#l702"></a>
<span id="l703">                    &quot;Server sent &quot; + ExtensionType.EXT_ALPN +</span><a href="#l703"></a>
<span id="l704">                    &quot; extension when not requested by client&quot;);</span><a href="#l704"></a>
<span id="l705">            }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">            List&lt;String&gt; protocols = serverHelloALPN.getPeerAPs();</span><a href="#l707"></a>
<span id="l708">            // Only one application protocol name should be present</span><a href="#l708"></a>
<span id="l709">            String p;</span><a href="#l709"></a>
<span id="l710">            if ((protocols.size() == 1) &amp;&amp;</span><a href="#l710"></a>
<span id="l711">                    !((p = protocols.get(0)).isEmpty())) {</span><a href="#l711"></a>
<span id="l712">                int i;</span><a href="#l712"></a>
<span id="l713">                for (i = 0; i &lt; localApl.length; i++) {</span><a href="#l713"></a>
<span id="l714">                    if (localApl[i].equals(p)) {</span><a href="#l714"></a>
<span id="l715">                        break;</span><a href="#l715"></a>
<span id="l716">                    }</span><a href="#l716"></a>
<span id="l717">                }</span><a href="#l717"></a>
<span id="l718">                if (i == localApl.length) {</span><a href="#l718"></a>
<span id="l719">                    fatalSE(Alerts.alert_handshake_failure,</span><a href="#l719"></a>
<span id="l720">                        &quot;Server has selected an application protocol name &quot; +</span><a href="#l720"></a>
<span id="l721">                        &quot;which was not offered by the client: &quot; + p);</span><a href="#l721"></a>
<span id="l722"></span><a href="#l722"></a>
<span id="l723">                }</span><a href="#l723"></a>
<span id="l724">                applicationProtocol = p;</span><a href="#l724"></a>
<span id="l725">            } else {</span><a href="#l725"></a>
<span id="l726">                fatalSE(Alerts.alert_handshake_failure,</span><a href="#l726"></a>
<span id="l727">                    &quot;Incorrect data in ServerHello &quot; + ExtensionType.EXT_ALPN +</span><a href="#l727"></a>
<span id="l728">                    &quot; message&quot;);</span><a href="#l728"></a>
<span id="l729">            }</span><a href="#l729"></a>
<span id="l730">        } else {</span><a href="#l730"></a>
<span id="l731">            applicationProtocol = &quot;&quot;;</span><a href="#l731"></a>
<span id="l732">        }</span><a href="#l732"></a>
<span id="l733"></span><a href="#l733"></a>
<span id="l734">        if (resumingSession &amp;&amp; session != null) {</span><a href="#l734"></a>
<span id="l735">            setHandshakeSessionSE(session);</span><a href="#l735"></a>
<span id="l736">            // Reserve the handshake state if this is a session-resumption</span><a href="#l736"></a>
<span id="l737">            // abbreviated initial handshake.</span><a href="#l737"></a>
<span id="l738">            if (isInitialHandshake) {</span><a href="#l738"></a>
<span id="l739">                session.setAsSessionResumption(true);</span><a href="#l739"></a>
<span id="l740">            }</span><a href="#l740"></a>
<span id="l741"></span><a href="#l741"></a>
<span id="l742">            return;</span><a href="#l742"></a>
<span id="l743">        }</span><a href="#l743"></a>
<span id="l744"></span><a href="#l744"></a>
<span id="l745">        // check extensions</span><a href="#l745"></a>
<span id="l746">        for (HelloExtension ext : mesg.extensions.list()) {</span><a href="#l746"></a>
<span id="l747">            ExtensionType type = ext.type;</span><a href="#l747"></a>
<span id="l748">            if (type == ExtensionType.EXT_SERVER_NAME) {</span><a href="#l748"></a>
<span id="l749">                serverNamesAccepted = true;</span><a href="#l749"></a>
<span id="l750">            } else if ((type != ExtensionType.EXT_ELLIPTIC_CURVES)</span><a href="#l750"></a>
<span id="l751">                    &amp;&amp; (type != ExtensionType.EXT_EC_POINT_FORMATS)</span><a href="#l751"></a>
<span id="l752">                    &amp;&amp; (type != ExtensionType.EXT_SERVER_NAME)</span><a href="#l752"></a>
<span id="l753">                    &amp;&amp; (type != ExtensionType.EXT_ALPN)</span><a href="#l753"></a>
<span id="l754">                    &amp;&amp; (type != ExtensionType.EXT_RENEGOTIATION_INFO)</span><a href="#l754"></a>
<span id="l755">                    &amp;&amp; (type != ExtensionType.EXT_EXTENDED_MASTER_SECRET)){</span><a href="#l755"></a>
<span id="l756">                fatalSE(Alerts.alert_unsupported_extension,</span><a href="#l756"></a>
<span id="l757">                    &quot;Server sent an unsupported extension: &quot; + type);</span><a href="#l757"></a>
<span id="l758">            }</span><a href="#l758"></a>
<span id="l759">        }</span><a href="#l759"></a>
<span id="l760"></span><a href="#l760"></a>
<span id="l761">        // Create a new session, we need to do the full handshake</span><a href="#l761"></a>
<span id="l762">        session = new SSLSessionImpl(protocolVersion, cipherSuite,</span><a href="#l762"></a>
<span id="l763">                            getLocalSupportedSignAlgs(),</span><a href="#l763"></a>
<span id="l764">                            mesg.sessionId, getHostSE(), getPortSE(),</span><a href="#l764"></a>
<span id="l765">                            (extendedMasterSecretExt != null),</span><a href="#l765"></a>
<span id="l766">                            getEndpointIdentificationAlgorithmSE());</span><a href="#l766"></a>
<span id="l767">        session.setRequestedServerNames(requestedServerNames);</span><a href="#l767"></a>
<span id="l768">        setHandshakeSessionSE(session);</span><a href="#l768"></a>
<span id="l769">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l769"></a>
<span id="l770">            System.out.println(&quot;** &quot; + cipherSuite);</span><a href="#l770"></a>
<span id="l771">        }</span><a href="#l771"></a>
<span id="l772">    }</span><a href="#l772"></a>
<span id="l773"></span><a href="#l773"></a>
<span id="l774">    /*</span><a href="#l774"></a>
<span id="l775">     * Server's own key was either a signing-only key, or was too</span><a href="#l775"></a>
<span id="l776">     * large for export rules ... this message holds an ephemeral</span><a href="#l776"></a>
<span id="l777">     * RSA key to use for key exchange.</span><a href="#l777"></a>
<span id="l778">     */</span><a href="#l778"></a>
<span id="l779">    private void serverKeyExchange(RSA_ServerKeyExchange mesg)</span><a href="#l779"></a>
<span id="l780">            throws IOException, GeneralSecurityException {</span><a href="#l780"></a>
<span id="l781">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l781"></a>
<span id="l782">            mesg.print(System.out);</span><a href="#l782"></a>
<span id="l783">        }</span><a href="#l783"></a>
<span id="l784">        if (!mesg.verify(serverKey, clnt_random, svr_random)) {</span><a href="#l784"></a>
<span id="l785">            fatalSE(Alerts.alert_handshake_failure,</span><a href="#l785"></a>
<span id="l786">                &quot;server key exchange invalid&quot;);</span><a href="#l786"></a>
<span id="l787">            // NOTREACHED</span><a href="#l787"></a>
<span id="l788">        }</span><a href="#l788"></a>
<span id="l789">        ephemeralServerKey = mesg.getPublicKey();</span><a href="#l789"></a>
<span id="l790"></span><a href="#l790"></a>
<span id="l791">        // check constraints of RSA PublicKey</span><a href="#l791"></a>
<span id="l792">        if (!algorithmConstraints.permits(</span><a href="#l792"></a>
<span id="l793">            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT), ephemeralServerKey)) {</span><a href="#l793"></a>
<span id="l794"></span><a href="#l794"></a>
<span id="l795">            throw new SSLHandshakeException(&quot;RSA ServerKeyExchange &quot; +</span><a href="#l795"></a>
<span id="l796">                    &quot;does not comply to algorithm constraints&quot;);</span><a href="#l796"></a>
<span id="l797">        }</span><a href="#l797"></a>
<span id="l798">    }</span><a href="#l798"></a>
<span id="l799"></span><a href="#l799"></a>
<span id="l800"></span><a href="#l800"></a>
<span id="l801">    /*</span><a href="#l801"></a>
<span id="l802">     * Diffie-Hellman key exchange.  We save the server public key and</span><a href="#l802"></a>
<span id="l803">     * our own D-H algorithm object so we can defer key calculations</span><a href="#l803"></a>
<span id="l804">     * until after we've sent the client key exchange message (which</span><a href="#l804"></a>
<span id="l805">     * gives client and server some useful parallelism).</span><a href="#l805"></a>
<span id="l806">     */</span><a href="#l806"></a>
<span id="l807">    private void serverKeyExchange(DH_ServerKeyExchange mesg)</span><a href="#l807"></a>
<span id="l808">            throws IOException {</span><a href="#l808"></a>
<span id="l809">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l809"></a>
<span id="l810">            mesg.print(System.out);</span><a href="#l810"></a>
<span id="l811">        }</span><a href="#l811"></a>
<span id="l812">        dh = new DHCrypt(mesg.getModulus(), mesg.getBase(),</span><a href="#l812"></a>
<span id="l813">                                            sslContext.getSecureRandom());</span><a href="#l813"></a>
<span id="l814">        serverDH = mesg.getServerPublicKey();</span><a href="#l814"></a>
<span id="l815"></span><a href="#l815"></a>
<span id="l816">        // check algorithm constraints</span><a href="#l816"></a>
<span id="l817">        dh.checkConstraints(algorithmConstraints, serverDH);</span><a href="#l817"></a>
<span id="l818">    }</span><a href="#l818"></a>
<span id="l819"></span><a href="#l819"></a>
<span id="l820">    private void serverKeyExchange(ECDH_ServerKeyExchange mesg)</span><a href="#l820"></a>
<span id="l821">            throws IOException {</span><a href="#l821"></a>
<span id="l822">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l822"></a>
<span id="l823">            mesg.print(System.out);</span><a href="#l823"></a>
<span id="l824">        }</span><a href="#l824"></a>
<span id="l825">        ECPublicKey key = mesg.getPublicKey();</span><a href="#l825"></a>
<span id="l826">        ecdh = new ECDHCrypt(key.getParams(), sslContext.getSecureRandom());</span><a href="#l826"></a>
<span id="l827">        ephemeralServerKey = key;</span><a href="#l827"></a>
<span id="l828"></span><a href="#l828"></a>
<span id="l829">        // check constraints of EC PublicKey</span><a href="#l829"></a>
<span id="l830">        if (!algorithmConstraints.permits(</span><a href="#l830"></a>
<span id="l831">            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT), ephemeralServerKey)) {</span><a href="#l831"></a>
<span id="l832"></span><a href="#l832"></a>
<span id="l833">            throw new SSLHandshakeException(&quot;ECDH ServerKeyExchange &quot; +</span><a href="#l833"></a>
<span id="l834">                    &quot;does not comply to algorithm constraints&quot;);</span><a href="#l834"></a>
<span id="l835">        }</span><a href="#l835"></a>
<span id="l836">    }</span><a href="#l836"></a>
<span id="l837"></span><a href="#l837"></a>
<span id="l838">    /*</span><a href="#l838"></a>
<span id="l839">     * The server's &quot;Hello Done&quot; message is the client's sign that</span><a href="#l839"></a>
<span id="l840">     * it's time to do all the hard work.</span><a href="#l840"></a>
<span id="l841">     */</span><a href="#l841"></a>
<span id="l842">    private void serverHelloDone(ServerHelloDone mesg) throws IOException {</span><a href="#l842"></a>
<span id="l843">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l843"></a>
<span id="l844">            mesg.print(System.out);</span><a href="#l844"></a>
<span id="l845">        }</span><a href="#l845"></a>
<span id="l846">        /*</span><a href="#l846"></a>
<span id="l847">         * Always make sure the input has been digested before we</span><a href="#l847"></a>
<span id="l848">         * start emitting data, to ensure the hashes are correctly</span><a href="#l848"></a>
<span id="l849">         * computed for the Finished and CertificateVerify messages</span><a href="#l849"></a>
<span id="l850">         * which we send (here).</span><a href="#l850"></a>
<span id="l851">         */</span><a href="#l851"></a>
<span id="l852">        input.digestNow();</span><a href="#l852"></a>
<span id="l853"></span><a href="#l853"></a>
<span id="l854">        /*</span><a href="#l854"></a>
<span id="l855">         * FIRST ... if requested, send an appropriate Certificate chain</span><a href="#l855"></a>
<span id="l856">         * to authenticate the client, and remember the associated private</span><a href="#l856"></a>
<span id="l857">         * key to sign the CertificateVerify message.</span><a href="#l857"></a>
<span id="l858">         */</span><a href="#l858"></a>
<span id="l859">        PrivateKey signingKey = null;</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">        if (certRequest != null) {</span><a href="#l861"></a>
<span id="l862">            X509ExtendedKeyManager km = sslContext.getX509KeyManager();</span><a href="#l862"></a>
<span id="l863"></span><a href="#l863"></a>
<span id="l864">            ArrayList&lt;String&gt; keytypesTmp = new ArrayList&lt;&gt;(4);</span><a href="#l864"></a>
<span id="l865"></span><a href="#l865"></a>
<span id="l866">            for (int i = 0; i &lt; certRequest.types.length; i++) {</span><a href="#l866"></a>
<span id="l867">                String typeName;</span><a href="#l867"></a>
<span id="l868"></span><a href="#l868"></a>
<span id="l869">                switch (certRequest.types[i]) {</span><a href="#l869"></a>
<span id="l870">                    case CertificateRequest.cct_rsa_sign:</span><a href="#l870"></a>
<span id="l871">                        typeName = &quot;RSA&quot;;</span><a href="#l871"></a>
<span id="l872">                        break;</span><a href="#l872"></a>
<span id="l873"></span><a href="#l873"></a>
<span id="l874">                    case CertificateRequest.cct_dss_sign:</span><a href="#l874"></a>
<span id="l875">                        typeName = &quot;DSA&quot;;</span><a href="#l875"></a>
<span id="l876">                            break;</span><a href="#l876"></a>
<span id="l877"></span><a href="#l877"></a>
<span id="l878">                    case CertificateRequest.cct_ecdsa_sign:</span><a href="#l878"></a>
<span id="l879">                        // ignore if we do not have EC crypto available</span><a href="#l879"></a>
<span id="l880">                        typeName = JsseJce.isEcAvailable() ? &quot;EC&quot; : null;</span><a href="#l880"></a>
<span id="l881">                        break;</span><a href="#l881"></a>
<span id="l882"></span><a href="#l882"></a>
<span id="l883">                    // Fixed DH/ECDH client authentication not supported</span><a href="#l883"></a>
<span id="l884">                    //</span><a href="#l884"></a>
<span id="l885">                    // case CertificateRequest.cct_rsa_fixed_dh:</span><a href="#l885"></a>
<span id="l886">                    // case CertificateRequest.cct_dss_fixed_dh:</span><a href="#l886"></a>
<span id="l887">                    // case CertificateRequest.cct_rsa_fixed_ecdh:</span><a href="#l887"></a>
<span id="l888">                    // case CertificateRequest.cct_ecdsa_fixed_ecdh:</span><a href="#l888"></a>
<span id="l889">                    //</span><a href="#l889"></a>
<span id="l890">                    // Any other values (currently not used in TLS)</span><a href="#l890"></a>
<span id="l891">                    //</span><a href="#l891"></a>
<span id="l892">                    // case CertificateRequest.cct_rsa_ephemeral_dh:</span><a href="#l892"></a>
<span id="l893">                    // case CertificateRequest.cct_dss_ephemeral_dh:</span><a href="#l893"></a>
<span id="l894">                    default:</span><a href="#l894"></a>
<span id="l895">                        typeName = null;</span><a href="#l895"></a>
<span id="l896">                        break;</span><a href="#l896"></a>
<span id="l897">                }</span><a href="#l897"></a>
<span id="l898"></span><a href="#l898"></a>
<span id="l899">                if ((typeName != null) &amp;&amp; (!keytypesTmp.contains(typeName))) {</span><a href="#l899"></a>
<span id="l900">                    keytypesTmp.add(typeName);</span><a href="#l900"></a>
<span id="l901">                }</span><a href="#l901"></a>
<span id="l902">            }</span><a href="#l902"></a>
<span id="l903"></span><a href="#l903"></a>
<span id="l904">            String alias = null;</span><a href="#l904"></a>
<span id="l905">            int keytypesTmpSize = keytypesTmp.size();</span><a href="#l905"></a>
<span id="l906">            if (keytypesTmpSize != 0) {</span><a href="#l906"></a>
<span id="l907">                String keytypes[] =</span><a href="#l907"></a>
<span id="l908">                        keytypesTmp.toArray(new String[keytypesTmpSize]);</span><a href="#l908"></a>
<span id="l909"></span><a href="#l909"></a>
<span id="l910">                if (conn != null) {</span><a href="#l910"></a>
<span id="l911">                    alias = km.chooseClientAlias(keytypes,</span><a href="#l911"></a>
<span id="l912">                        certRequest.getAuthorities(), conn);</span><a href="#l912"></a>
<span id="l913">                } else {</span><a href="#l913"></a>
<span id="l914">                    alias = km.chooseEngineClientAlias(keytypes,</span><a href="#l914"></a>
<span id="l915">                        certRequest.getAuthorities(), engine);</span><a href="#l915"></a>
<span id="l916">                }</span><a href="#l916"></a>
<span id="l917">            }</span><a href="#l917"></a>
<span id="l918"></span><a href="#l918"></a>
<span id="l919">            CertificateMsg m1 = null;</span><a href="#l919"></a>
<span id="l920">            if (alias != null) {</span><a href="#l920"></a>
<span id="l921">                X509Certificate[] certs = km.getCertificateChain(alias);</span><a href="#l921"></a>
<span id="l922">                if ((certs != null) &amp;&amp; (certs.length != 0)) {</span><a href="#l922"></a>
<span id="l923">                    PublicKey publicKey = certs[0].getPublicKey();</span><a href="#l923"></a>
<span id="l924">                    if (publicKey != null) {</span><a href="#l924"></a>
<span id="l925">                        m1 = new CertificateMsg(certs);</span><a href="#l925"></a>
<span id="l926">                        signingKey = km.getPrivateKey(alias);</span><a href="#l926"></a>
<span id="l927">                        session.setLocalPrivateKey(signingKey);</span><a href="#l927"></a>
<span id="l928">                        session.setLocalCertificates(certs);</span><a href="#l928"></a>
<span id="l929">                    }</span><a href="#l929"></a>
<span id="l930">                }</span><a href="#l930"></a>
<span id="l931">            }</span><a href="#l931"></a>
<span id="l932">            if (m1 == null) {</span><a href="#l932"></a>
<span id="l933">                //</span><a href="#l933"></a>
<span id="l934">                // No appropriate cert was found ... report this to the</span><a href="#l934"></a>
<span id="l935">                // server.  For SSLv3, send the no_certificate alert;</span><a href="#l935"></a>
<span id="l936">                // TLS uses an empty cert chain instead.</span><a href="#l936"></a>
<span id="l937">                //</span><a href="#l937"></a>
<span id="l938">                if (protocolVersion.v &gt;= ProtocolVersion.TLS10.v) {</span><a href="#l938"></a>
<span id="l939">                    m1 = new CertificateMsg(new X509Certificate [0]);</span><a href="#l939"></a>
<span id="l940">                } else {</span><a href="#l940"></a>
<span id="l941">                    warningSE(Alerts.alert_no_certificate);</span><a href="#l941"></a>
<span id="l942">                }</span><a href="#l942"></a>
<span id="l943">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l943"></a>
<span id="l944">                    System.out.println(</span><a href="#l944"></a>
<span id="l945">                        &quot;Warning: no suitable certificate found - &quot; +</span><a href="#l945"></a>
<span id="l946">                        &quot;continuing without client authentication&quot;);</span><a href="#l946"></a>
<span id="l947">                }</span><a href="#l947"></a>
<span id="l948">            }</span><a href="#l948"></a>
<span id="l949"></span><a href="#l949"></a>
<span id="l950">            //</span><a href="#l950"></a>
<span id="l951">            // At last ... send any client certificate chain.</span><a href="#l951"></a>
<span id="l952">            //</span><a href="#l952"></a>
<span id="l953">            if (m1 != null) {</span><a href="#l953"></a>
<span id="l954">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l954"></a>
<span id="l955">                    m1.print(System.out);</span><a href="#l955"></a>
<span id="l956">                }</span><a href="#l956"></a>
<span id="l957">                m1.write(output);</span><a href="#l957"></a>
<span id="l958">                handshakeState.update(m1, resumingSession);</span><a href="#l958"></a>
<span id="l959">            }</span><a href="#l959"></a>
<span id="l960">        }</span><a href="#l960"></a>
<span id="l961"></span><a href="#l961"></a>
<span id="l962">        /*</span><a href="#l962"></a>
<span id="l963">         * SECOND ... send the client key exchange message.  The</span><a href="#l963"></a>
<span id="l964">         * procedure used is a function of the cipher suite selected;</span><a href="#l964"></a>
<span id="l965">         * one is always needed.</span><a href="#l965"></a>
<span id="l966">         */</span><a href="#l966"></a>
<span id="l967">        HandshakeMessage m2;</span><a href="#l967"></a>
<span id="l968"></span><a href="#l968"></a>
<span id="l969">        switch (keyExchange) {</span><a href="#l969"></a>
<span id="l970"></span><a href="#l970"></a>
<span id="l971">        case K_RSA:</span><a href="#l971"></a>
<span id="l972">        case K_RSA_EXPORT:</span><a href="#l972"></a>
<span id="l973">            if (serverKey == null) {</span><a href="#l973"></a>
<span id="l974">                throw new SSLProtocolException</span><a href="#l974"></a>
<span id="l975">                        (&quot;Server did not send certificate message&quot;);</span><a href="#l975"></a>
<span id="l976">            }</span><a href="#l976"></a>
<span id="l977"></span><a href="#l977"></a>
<span id="l978">            if (!(serverKey instanceof RSAPublicKey)) {</span><a href="#l978"></a>
<span id="l979">                throw new SSLProtocolException</span><a href="#l979"></a>
<span id="l980">                        (&quot;Server certificate does not include an RSA key&quot;);</span><a href="#l980"></a>
<span id="l981">            }</span><a href="#l981"></a>
<span id="l982"></span><a href="#l982"></a>
<span id="l983">            /*</span><a href="#l983"></a>
<span id="l984">             * For RSA key exchange, we randomly generate a new</span><a href="#l984"></a>
<span id="l985">             * pre-master secret and encrypt it with the server's</span><a href="#l985"></a>
<span id="l986">             * public key.  Then we save that pre-master secret</span><a href="#l986"></a>
<span id="l987">             * so that we can calculate the keying data later;</span><a href="#l987"></a>
<span id="l988">             * it's a performance speedup not to do that until</span><a href="#l988"></a>
<span id="l989">             * the client's waiting for the server response, but</span><a href="#l989"></a>
<span id="l990">             * more of a speedup for the D-H case.</span><a href="#l990"></a>
<span id="l991">             *</span><a href="#l991"></a>
<span id="l992">             * If the RSA_EXPORT scheme is active, when the public</span><a href="#l992"></a>
<span id="l993">             * key in the server certificate is less than or equal</span><a href="#l993"></a>
<span id="l994">             * to 512 bits in length, use the cert's public key,</span><a href="#l994"></a>
<span id="l995">             * otherwise, the ephemeral one.</span><a href="#l995"></a>
<span id="l996">             */</span><a href="#l996"></a>
<span id="l997">            PublicKey key;</span><a href="#l997"></a>
<span id="l998">            if (keyExchange == K_RSA) {</span><a href="#l998"></a>
<span id="l999">                key = serverKey;</span><a href="#l999"></a>
<span id="l1000">            } else {    // K_RSA_EXPORT</span><a href="#l1000"></a>
<span id="l1001">                if (JsseJce.getRSAKeyLength(serverKey) &lt;= 512) {</span><a href="#l1001"></a>
<span id="l1002">                    // extraneous ephemeralServerKey check done</span><a href="#l1002"></a>
<span id="l1003">                    // above in processMessage()</span><a href="#l1003"></a>
<span id="l1004">                    key = serverKey;</span><a href="#l1004"></a>
<span id="l1005">                } else {</span><a href="#l1005"></a>
<span id="l1006">                    if (ephemeralServerKey == null) {</span><a href="#l1006"></a>
<span id="l1007">                        throw new SSLProtocolException(&quot;Server did not send&quot; +</span><a href="#l1007"></a>
<span id="l1008">                            &quot; a RSA_EXPORT Server Key Exchange message&quot;);</span><a href="#l1008"></a>
<span id="l1009">                    }</span><a href="#l1009"></a>
<span id="l1010">                    key = ephemeralServerKey;</span><a href="#l1010"></a>
<span id="l1011">                }</span><a href="#l1011"></a>
<span id="l1012">            }</span><a href="#l1012"></a>
<span id="l1013"></span><a href="#l1013"></a>
<span id="l1014">            m2 = new RSAClientKeyExchange(protocolVersion, maxProtocolVersion,</span><a href="#l1014"></a>
<span id="l1015">                                sslContext.getSecureRandom(), key);</span><a href="#l1015"></a>
<span id="l1016">            break;</span><a href="#l1016"></a>
<span id="l1017">        case K_DH_RSA:</span><a href="#l1017"></a>
<span id="l1018">        case K_DH_DSS:</span><a href="#l1018"></a>
<span id="l1019">            /*</span><a href="#l1019"></a>
<span id="l1020">             * For DH Key exchange, we only need to make sure the server</span><a href="#l1020"></a>
<span id="l1021">             * knows our public key, so we calculate the same pre-master</span><a href="#l1021"></a>
<span id="l1022">             * secret.</span><a href="#l1022"></a>
<span id="l1023">             *</span><a href="#l1023"></a>
<span id="l1024">             * For certs that had DH keys in them, we send an empty</span><a href="#l1024"></a>
<span id="l1025">             * handshake message (no key) ... we flag this case by</span><a href="#l1025"></a>
<span id="l1026">             * passing a null &quot;dhPublic&quot; value.</span><a href="#l1026"></a>
<span id="l1027">             *</span><a href="#l1027"></a>
<span id="l1028">             * Otherwise we send ephemeral DH keys, unsigned.</span><a href="#l1028"></a>
<span id="l1029">             */</span><a href="#l1029"></a>
<span id="l1030">            // if (useDH_RSA || useDH_DSS)</span><a href="#l1030"></a>
<span id="l1031">            m2 = new DHClientKeyExchange();</span><a href="#l1031"></a>
<span id="l1032">            break;</span><a href="#l1032"></a>
<span id="l1033">        case K_DHE_RSA:</span><a href="#l1033"></a>
<span id="l1034">        case K_DHE_DSS:</span><a href="#l1034"></a>
<span id="l1035">        case K_DH_ANON:</span><a href="#l1035"></a>
<span id="l1036">            if (dh == null) {</span><a href="#l1036"></a>
<span id="l1037">                throw new SSLProtocolException</span><a href="#l1037"></a>
<span id="l1038">                    (&quot;Server did not send a DH Server Key Exchange message&quot;);</span><a href="#l1038"></a>
<span id="l1039">            }</span><a href="#l1039"></a>
<span id="l1040">            m2 = new DHClientKeyExchange(dh.getPublicKey());</span><a href="#l1040"></a>
<span id="l1041">            break;</span><a href="#l1041"></a>
<span id="l1042">        case K_ECDHE_RSA:</span><a href="#l1042"></a>
<span id="l1043">        case K_ECDHE_ECDSA:</span><a href="#l1043"></a>
<span id="l1044">        case K_ECDH_ANON:</span><a href="#l1044"></a>
<span id="l1045">            if (ecdh == null) {</span><a href="#l1045"></a>
<span id="l1046">                throw new SSLProtocolException</span><a href="#l1046"></a>
<span id="l1047">                    (&quot;Server did not send a ECDH Server Key Exchange message&quot;);</span><a href="#l1047"></a>
<span id="l1048">            }</span><a href="#l1048"></a>
<span id="l1049">            m2 = new ECDHClientKeyExchange(ecdh.getPublicKey());</span><a href="#l1049"></a>
<span id="l1050">            break;</span><a href="#l1050"></a>
<span id="l1051">        case K_ECDH_RSA:</span><a href="#l1051"></a>
<span id="l1052">        case K_ECDH_ECDSA:</span><a href="#l1052"></a>
<span id="l1053">            if (serverKey == null) {</span><a href="#l1053"></a>
<span id="l1054">                throw new SSLProtocolException</span><a href="#l1054"></a>
<span id="l1055">                        (&quot;Server did not send certificate message&quot;);</span><a href="#l1055"></a>
<span id="l1056">            }</span><a href="#l1056"></a>
<span id="l1057">            if (serverKey instanceof ECPublicKey == false) {</span><a href="#l1057"></a>
<span id="l1058">                throw new SSLProtocolException</span><a href="#l1058"></a>
<span id="l1059">                        (&quot;Server certificate does not include an EC key&quot;);</span><a href="#l1059"></a>
<span id="l1060">            }</span><a href="#l1060"></a>
<span id="l1061">            ECParameterSpec params = ((ECPublicKey)serverKey).getParams();</span><a href="#l1061"></a>
<span id="l1062">            ecdh = new ECDHCrypt(params, sslContext.getSecureRandom());</span><a href="#l1062"></a>
<span id="l1063">            m2 = new ECDHClientKeyExchange(ecdh.getPublicKey());</span><a href="#l1063"></a>
<span id="l1064">            break;</span><a href="#l1064"></a>
<span id="l1065">        case K_KRB5:</span><a href="#l1065"></a>
<span id="l1066">        case K_KRB5_EXPORT:</span><a href="#l1066"></a>
<span id="l1067">            String sniHostname = null;</span><a href="#l1067"></a>
<span id="l1068">            for (SNIServerName serverName : requestedServerNames) {</span><a href="#l1068"></a>
<span id="l1069">                if (serverName instanceof SNIHostName) {</span><a href="#l1069"></a>
<span id="l1070">                    sniHostname = ((SNIHostName) serverName).getAsciiName();</span><a href="#l1070"></a>
<span id="l1071">                    break;</span><a href="#l1071"></a>
<span id="l1072">                }</span><a href="#l1072"></a>
<span id="l1073">            }</span><a href="#l1073"></a>
<span id="l1074"></span><a href="#l1074"></a>
<span id="l1075">            KerberosClientKeyExchange kerberosMsg = null;</span><a href="#l1075"></a>
<span id="l1076">            if (sniHostname != null) {</span><a href="#l1076"></a>
<span id="l1077">                // use first requested SNI hostname</span><a href="#l1077"></a>
<span id="l1078">                try {</span><a href="#l1078"></a>
<span id="l1079">                    kerberosMsg = new KerberosClientKeyExchange(</span><a href="#l1079"></a>
<span id="l1080">                        sniHostname, getAccSE(), protocolVersion,</span><a href="#l1080"></a>
<span id="l1081">                        sslContext.getSecureRandom());</span><a href="#l1081"></a>
<span id="l1082">                } catch(IOException e) {</span><a href="#l1082"></a>
<span id="l1083">                    if (serverNamesAccepted) {</span><a href="#l1083"></a>
<span id="l1084">                        // server accepted requested SNI hostname,</span><a href="#l1084"></a>
<span id="l1085">                        // so it must be used</span><a href="#l1085"></a>
<span id="l1086">                        throw e;</span><a href="#l1086"></a>
<span id="l1087">                    }</span><a href="#l1087"></a>
<span id="l1088">                    // fallback to using hostname</span><a href="#l1088"></a>
<span id="l1089">                    if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1089"></a>
<span id="l1090">                        System.out.println(</span><a href="#l1090"></a>
<span id="l1091">                            &quot;Warning, cannot use Server Name Indication: &quot;</span><a href="#l1091"></a>
<span id="l1092">                                + e.getMessage());</span><a href="#l1092"></a>
<span id="l1093">                    }</span><a href="#l1093"></a>
<span id="l1094">                }</span><a href="#l1094"></a>
<span id="l1095">            }</span><a href="#l1095"></a>
<span id="l1096"></span><a href="#l1096"></a>
<span id="l1097">            if (kerberosMsg == null) {</span><a href="#l1097"></a>
<span id="l1098">                String hostname = getHostSE();</span><a href="#l1098"></a>
<span id="l1099">                if (hostname == null) {</span><a href="#l1099"></a>
<span id="l1100">                    throw new IOException(&quot;Hostname is required&quot; +</span><a href="#l1100"></a>
<span id="l1101">                        &quot; to use Kerberos cipher suites&quot;);</span><a href="#l1101"></a>
<span id="l1102">                }</span><a href="#l1102"></a>
<span id="l1103">                kerberosMsg = new KerberosClientKeyExchange(</span><a href="#l1103"></a>
<span id="l1104">                     hostname, getAccSE(), protocolVersion,</span><a href="#l1104"></a>
<span id="l1105">                     sslContext.getSecureRandom());</span><a href="#l1105"></a>
<span id="l1106">            }</span><a href="#l1106"></a>
<span id="l1107"></span><a href="#l1107"></a>
<span id="l1108">            // Record the principals involved in exchange</span><a href="#l1108"></a>
<span id="l1109">            session.setPeerPrincipal(kerberosMsg.getPeerPrincipal());</span><a href="#l1109"></a>
<span id="l1110">            session.setLocalPrincipal(kerberosMsg.getLocalPrincipal());</span><a href="#l1110"></a>
<span id="l1111">            m2 = kerberosMsg;</span><a href="#l1111"></a>
<span id="l1112">            break;</span><a href="#l1112"></a>
<span id="l1113">        default:</span><a href="#l1113"></a>
<span id="l1114">            // somethings very wrong</span><a href="#l1114"></a>
<span id="l1115">            throw new RuntimeException</span><a href="#l1115"></a>
<span id="l1116">                                (&quot;Unsupported key exchange: &quot; + keyExchange);</span><a href="#l1116"></a>
<span id="l1117">        }</span><a href="#l1117"></a>
<span id="l1118">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1118"></a>
<span id="l1119">            m2.print(System.out);</span><a href="#l1119"></a>
<span id="l1120">        }</span><a href="#l1120"></a>
<span id="l1121">        m2.write(output);</span><a href="#l1121"></a>
<span id="l1122"></span><a href="#l1122"></a>
<span id="l1123">        handshakeState.update(m2, resumingSession);</span><a href="#l1123"></a>
<span id="l1124"></span><a href="#l1124"></a>
<span id="l1125">        /*</span><a href="#l1125"></a>
<span id="l1126">         * THIRD, send a &quot;change_cipher_spec&quot; record followed by the</span><a href="#l1126"></a>
<span id="l1127">         * &quot;Finished&quot; message.  We flush the messages we've queued up, to</span><a href="#l1127"></a>
<span id="l1128">         * get concurrency between client and server.  The concurrency is</span><a href="#l1128"></a>
<span id="l1129">         * useful as we calculate the master secret, which is needed both</span><a href="#l1129"></a>
<span id="l1130">         * to compute the &quot;Finished&quot; message, and to compute the keys used</span><a href="#l1130"></a>
<span id="l1131">         * to protect all records following the change_cipher_spec.</span><a href="#l1131"></a>
<span id="l1132">         */</span><a href="#l1132"></a>
<span id="l1133"></span><a href="#l1133"></a>
<span id="l1134">        output.doHashes();</span><a href="#l1134"></a>
<span id="l1135">        output.flush();</span><a href="#l1135"></a>
<span id="l1136"></span><a href="#l1136"></a>
<span id="l1137">        /*</span><a href="#l1137"></a>
<span id="l1138">         * We deferred calculating the master secret and this connection's</span><a href="#l1138"></a>
<span id="l1139">         * keying data; we do it now.  Deferring this calculation is good</span><a href="#l1139"></a>
<span id="l1140">         * from a performance point of view, since it lets us do it during</span><a href="#l1140"></a>
<span id="l1141">         * some time that network delays and the server's own calculations</span><a href="#l1141"></a>
<span id="l1142">         * would otherwise cause to be &quot;dead&quot; in the critical path.</span><a href="#l1142"></a>
<span id="l1143">         */</span><a href="#l1143"></a>
<span id="l1144">        SecretKey preMasterSecret;</span><a href="#l1144"></a>
<span id="l1145">        switch (keyExchange) {</span><a href="#l1145"></a>
<span id="l1146">        case K_RSA:</span><a href="#l1146"></a>
<span id="l1147">        case K_RSA_EXPORT:</span><a href="#l1147"></a>
<span id="l1148">            preMasterSecret = ((RSAClientKeyExchange)m2).preMaster;</span><a href="#l1148"></a>
<span id="l1149">            break;</span><a href="#l1149"></a>
<span id="l1150">        case K_KRB5:</span><a href="#l1150"></a>
<span id="l1151">        case K_KRB5_EXPORT:</span><a href="#l1151"></a>
<span id="l1152">            byte[] secretBytes =</span><a href="#l1152"></a>
<span id="l1153">                ((KerberosClientKeyExchange)m2).getUnencryptedPreMasterSecret();</span><a href="#l1153"></a>
<span id="l1154">            preMasterSecret = new SecretKeySpec(secretBytes,</span><a href="#l1154"></a>
<span id="l1155">                &quot;TlsPremasterSecret&quot;);</span><a href="#l1155"></a>
<span id="l1156">            break;</span><a href="#l1156"></a>
<span id="l1157">        case K_DHE_RSA:</span><a href="#l1157"></a>
<span id="l1158">        case K_DHE_DSS:</span><a href="#l1158"></a>
<span id="l1159">        case K_DH_ANON:</span><a href="#l1159"></a>
<span id="l1160">            preMasterSecret = dh.getAgreedSecret(serverDH, true);</span><a href="#l1160"></a>
<span id="l1161">            break;</span><a href="#l1161"></a>
<span id="l1162">        case K_ECDHE_RSA:</span><a href="#l1162"></a>
<span id="l1163">        case K_ECDHE_ECDSA:</span><a href="#l1163"></a>
<span id="l1164">        case K_ECDH_ANON:</span><a href="#l1164"></a>
<span id="l1165">            preMasterSecret = ecdh.getAgreedSecret(ephemeralServerKey);</span><a href="#l1165"></a>
<span id="l1166">            break;</span><a href="#l1166"></a>
<span id="l1167">        case K_ECDH_RSA:</span><a href="#l1167"></a>
<span id="l1168">        case K_ECDH_ECDSA:</span><a href="#l1168"></a>
<span id="l1169">            preMasterSecret = ecdh.getAgreedSecret(serverKey);</span><a href="#l1169"></a>
<span id="l1170">            break;</span><a href="#l1170"></a>
<span id="l1171">        default:</span><a href="#l1171"></a>
<span id="l1172">            throw new IOException(&quot;Internal error: unknown key exchange &quot;</span><a href="#l1172"></a>
<span id="l1173">                + keyExchange);</span><a href="#l1173"></a>
<span id="l1174">        }</span><a href="#l1174"></a>
<span id="l1175"></span><a href="#l1175"></a>
<span id="l1176">        calculateKeys(preMasterSecret, null);</span><a href="#l1176"></a>
<span id="l1177"></span><a href="#l1177"></a>
<span id="l1178">        /*</span><a href="#l1178"></a>
<span id="l1179">         * FOURTH, if we sent a Certificate, we need to send a signed</span><a href="#l1179"></a>
<span id="l1180">         * CertificateVerify (unless the key in the client's certificate</span><a href="#l1180"></a>
<span id="l1181">         * was a Diffie-Hellman key).).</span><a href="#l1181"></a>
<span id="l1182">         *</span><a href="#l1182"></a>
<span id="l1183">         * This uses a hash of the previous handshake messages ... either</span><a href="#l1183"></a>
<span id="l1184">         * a nonfinal one (if the particular implementation supports it)</span><a href="#l1184"></a>
<span id="l1185">         * or else using the third element in the arrays of hashes being</span><a href="#l1185"></a>
<span id="l1186">         * computed.</span><a href="#l1186"></a>
<span id="l1187">         */</span><a href="#l1187"></a>
<span id="l1188">        if (signingKey != null) {</span><a href="#l1188"></a>
<span id="l1189">            CertificateVerify m3;</span><a href="#l1189"></a>
<span id="l1190">            try {</span><a href="#l1190"></a>
<span id="l1191">                SignatureAndHashAlgorithm preferableSignatureAlgorithm = null;</span><a href="#l1191"></a>
<span id="l1192">                if (protocolVersion.v &gt;= ProtocolVersion.TLS12.v) {</span><a href="#l1192"></a>
<span id="l1193">                    preferableSignatureAlgorithm =</span><a href="#l1193"></a>
<span id="l1194">                        SignatureAndHashAlgorithm.getPreferableAlgorithm(</span><a href="#l1194"></a>
<span id="l1195">                            getPeerSupportedSignAlgs(),</span><a href="#l1195"></a>
<span id="l1196">                            signingKey.getAlgorithm(), signingKey);</span><a href="#l1196"></a>
<span id="l1197"></span><a href="#l1197"></a>
<span id="l1198">                    if (preferableSignatureAlgorithm == null) {</span><a href="#l1198"></a>
<span id="l1199">                        throw new SSLHandshakeException(</span><a href="#l1199"></a>
<span id="l1200">                            &quot;No supported signature algorithm&quot;);</span><a href="#l1200"></a>
<span id="l1201">                    }</span><a href="#l1201"></a>
<span id="l1202"></span><a href="#l1202"></a>
<span id="l1203">                    String hashAlg =</span><a href="#l1203"></a>
<span id="l1204">                        SignatureAndHashAlgorithm.getHashAlgorithmName(</span><a href="#l1204"></a>
<span id="l1205">                                preferableSignatureAlgorithm);</span><a href="#l1205"></a>
<span id="l1206">                    if (hashAlg == null || hashAlg.length() == 0) {</span><a href="#l1206"></a>
<span id="l1207">                        throw new SSLHandshakeException(</span><a href="#l1207"></a>
<span id="l1208">                                &quot;No supported hash algorithm&quot;);</span><a href="#l1208"></a>
<span id="l1209">                    }</span><a href="#l1209"></a>
<span id="l1210">                }</span><a href="#l1210"></a>
<span id="l1211"></span><a href="#l1211"></a>
<span id="l1212">                m3 = new CertificateVerify(protocolVersion, handshakeHash,</span><a href="#l1212"></a>
<span id="l1213">                    signingKey, session.getMasterSecret(),</span><a href="#l1213"></a>
<span id="l1214">                    sslContext.getSecureRandom(),</span><a href="#l1214"></a>
<span id="l1215">                    preferableSignatureAlgorithm);</span><a href="#l1215"></a>
<span id="l1216">            } catch (GeneralSecurityException e) {</span><a href="#l1216"></a>
<span id="l1217">                fatalSE(Alerts.alert_handshake_failure,</span><a href="#l1217"></a>
<span id="l1218">                    &quot;Error signing certificate verify&quot;, e);</span><a href="#l1218"></a>
<span id="l1219">                // NOTREACHED, make compiler happy</span><a href="#l1219"></a>
<span id="l1220">                m3 = null;</span><a href="#l1220"></a>
<span id="l1221">            }</span><a href="#l1221"></a>
<span id="l1222">            if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1222"></a>
<span id="l1223">                m3.print(System.out);</span><a href="#l1223"></a>
<span id="l1224">            }</span><a href="#l1224"></a>
<span id="l1225">            m3.write(output);</span><a href="#l1225"></a>
<span id="l1226">            handshakeState.update(m3, resumingSession);</span><a href="#l1226"></a>
<span id="l1227">            output.doHashes();</span><a href="#l1227"></a>
<span id="l1228">        }</span><a href="#l1228"></a>
<span id="l1229"></span><a href="#l1229"></a>
<span id="l1230">        /*</span><a href="#l1230"></a>
<span id="l1231">         * OK, that's that!</span><a href="#l1231"></a>
<span id="l1232">         */</span><a href="#l1232"></a>
<span id="l1233">        sendChangeCipherAndFinish(false);</span><a href="#l1233"></a>
<span id="l1234">    }</span><a href="#l1234"></a>
<span id="l1235"></span><a href="#l1235"></a>
<span id="l1236"></span><a href="#l1236"></a>
<span id="l1237">    /*</span><a href="#l1237"></a>
<span id="l1238">     * &quot;Finished&quot; is the last handshake message sent.  If we got this</span><a href="#l1238"></a>
<span id="l1239">     * far, the MAC has been validated post-decryption.  We validate</span><a href="#l1239"></a>
<span id="l1240">     * the two hashes here as an additional sanity check, protecting</span><a href="#l1240"></a>
<span id="l1241">     * the handshake against various active attacks.</span><a href="#l1241"></a>
<span id="l1242">     */</span><a href="#l1242"></a>
<span id="l1243">    private void serverFinished(Finished mesg) throws IOException {</span><a href="#l1243"></a>
<span id="l1244">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1244"></a>
<span id="l1245">            mesg.print(System.out);</span><a href="#l1245"></a>
<span id="l1246">        }</span><a href="#l1246"></a>
<span id="l1247"></span><a href="#l1247"></a>
<span id="l1248">        boolean verified = mesg.verify(handshakeHash, Finished.SERVER,</span><a href="#l1248"></a>
<span id="l1249">            session.getMasterSecret());</span><a href="#l1249"></a>
<span id="l1250"></span><a href="#l1250"></a>
<span id="l1251">        if (!verified) {</span><a href="#l1251"></a>
<span id="l1252">            fatalSE(Alerts.alert_illegal_parameter,</span><a href="#l1252"></a>
<span id="l1253">                       &quot;server 'finished' message doesn't verify&quot;);</span><a href="#l1253"></a>
<span id="l1254">            // NOTREACHED</span><a href="#l1254"></a>
<span id="l1255">        }</span><a href="#l1255"></a>
<span id="l1256"></span><a href="#l1256"></a>
<span id="l1257">        /*</span><a href="#l1257"></a>
<span id="l1258">         * save server verify data for secure renegotiation</span><a href="#l1258"></a>
<span id="l1259">         */</span><a href="#l1259"></a>
<span id="l1260">        if (secureRenegotiation) {</span><a href="#l1260"></a>
<span id="l1261">            serverVerifyData = mesg.getVerifyData();</span><a href="#l1261"></a>
<span id="l1262">        }</span><a href="#l1262"></a>
<span id="l1263"></span><a href="#l1263"></a>
<span id="l1264">        /*</span><a href="#l1264"></a>
<span id="l1265">         * Reset the handshake state if this is not an initial handshake.</span><a href="#l1265"></a>
<span id="l1266">         */</span><a href="#l1266"></a>
<span id="l1267">        if (!isInitialHandshake) {</span><a href="#l1267"></a>
<span id="l1268">            session.setAsSessionResumption(false);</span><a href="#l1268"></a>
<span id="l1269">        }</span><a href="#l1269"></a>
<span id="l1270"></span><a href="#l1270"></a>
<span id="l1271">        /*</span><a href="#l1271"></a>
<span id="l1272">         * OK, it verified.  If we're doing the fast handshake, add that</span><a href="#l1272"></a>
<span id="l1273">         * &quot;Finished&quot; message to the hash of handshake messages, then send</span><a href="#l1273"></a>
<span id="l1274">         * our own change_cipher_spec and Finished message for the server</span><a href="#l1274"></a>
<span id="l1275">         * to verify in turn.  These are the last handshake messages.</span><a href="#l1275"></a>
<span id="l1276">         *</span><a href="#l1276"></a>
<span id="l1277">         * In any case, update the session cache.  We're done handshaking,</span><a href="#l1277"></a>
<span id="l1278">         * so there are no threats any more associated with partially</span><a href="#l1278"></a>
<span id="l1279">         * completed handshakes.</span><a href="#l1279"></a>
<span id="l1280">         */</span><a href="#l1280"></a>
<span id="l1281">        if (resumingSession) {</span><a href="#l1281"></a>
<span id="l1282">            input.digestNow();</span><a href="#l1282"></a>
<span id="l1283">            sendChangeCipherAndFinish(true);</span><a href="#l1283"></a>
<span id="l1284">        } else {</span><a href="#l1284"></a>
<span id="l1285">            handshakeFinished = true;</span><a href="#l1285"></a>
<span id="l1286">        }</span><a href="#l1286"></a>
<span id="l1287">        session.setLastAccessedTime(System.currentTimeMillis());</span><a href="#l1287"></a>
<span id="l1288"></span><a href="#l1288"></a>
<span id="l1289">        if (!resumingSession) {</span><a href="#l1289"></a>
<span id="l1290">            if (session.isRejoinable()) {</span><a href="#l1290"></a>
<span id="l1291">                ((SSLSessionContextImpl) sslContext</span><a href="#l1291"></a>
<span id="l1292">                        .engineGetClientSessionContext())</span><a href="#l1292"></a>
<span id="l1293">                        .put(session);</span><a href="#l1293"></a>
<span id="l1294">                if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1294"></a>
<span id="l1295">                    System.out.println(&quot;%% Cached client session: &quot; + session);</span><a href="#l1295"></a>
<span id="l1296">                }</span><a href="#l1296"></a>
<span id="l1297">            } else if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1297"></a>
<span id="l1298">                System.out.println(</span><a href="#l1298"></a>
<span id="l1299">                    &quot;%% Didn't cache non-resumable client session: &quot;</span><a href="#l1299"></a>
<span id="l1300">                    + session);</span><a href="#l1300"></a>
<span id="l1301">            }</span><a href="#l1301"></a>
<span id="l1302">        }</span><a href="#l1302"></a>
<span id="l1303">    }</span><a href="#l1303"></a>
<span id="l1304"></span><a href="#l1304"></a>
<span id="l1305"></span><a href="#l1305"></a>
<span id="l1306">    /*</span><a href="#l1306"></a>
<span id="l1307">     * Send my change-cipher-spec and Finished message ... done as the</span><a href="#l1307"></a>
<span id="l1308">     * last handshake act in either the short or long sequences.  In</span><a href="#l1308"></a>
<span id="l1309">     * the short one, we've already seen the server's Finished; in the</span><a href="#l1309"></a>
<span id="l1310">     * long one, we wait for it now.</span><a href="#l1310"></a>
<span id="l1311">     */</span><a href="#l1311"></a>
<span id="l1312">    private void sendChangeCipherAndFinish(boolean finishedTag)</span><a href="#l1312"></a>
<span id="l1313">            throws IOException {</span><a href="#l1313"></a>
<span id="l1314">        Finished mesg = new Finished(protocolVersion, handshakeHash,</span><a href="#l1314"></a>
<span id="l1315">            Finished.CLIENT, session.getMasterSecret(), cipherSuite);</span><a href="#l1315"></a>
<span id="l1316"></span><a href="#l1316"></a>
<span id="l1317">        /*</span><a href="#l1317"></a>
<span id="l1318">         * Send the change_cipher_spec message, then the Finished message</span><a href="#l1318"></a>
<span id="l1319">         * which we just calculated (and protected using the keys we just</span><a href="#l1319"></a>
<span id="l1320">         * calculated).  Server responds with its Finished message, except</span><a href="#l1320"></a>
<span id="l1321">         * in the &quot;fast handshake&quot; (resume session) case.</span><a href="#l1321"></a>
<span id="l1322">         */</span><a href="#l1322"></a>
<span id="l1323">        sendChangeCipherSpec(mesg, finishedTag);</span><a href="#l1323"></a>
<span id="l1324"></span><a href="#l1324"></a>
<span id="l1325">        /*</span><a href="#l1325"></a>
<span id="l1326">         * save client verify data for secure renegotiation</span><a href="#l1326"></a>
<span id="l1327">         */</span><a href="#l1327"></a>
<span id="l1328">        if (secureRenegotiation) {</span><a href="#l1328"></a>
<span id="l1329">            clientVerifyData = mesg.getVerifyData();</span><a href="#l1329"></a>
<span id="l1330">        }</span><a href="#l1330"></a>
<span id="l1331">    }</span><a href="#l1331"></a>
<span id="l1332"></span><a href="#l1332"></a>
<span id="l1333"></span><a href="#l1333"></a>
<span id="l1334">    /*</span><a href="#l1334"></a>
<span id="l1335">     * Returns a ClientHello message to kickstart renegotiations</span><a href="#l1335"></a>
<span id="l1336">     */</span><a href="#l1336"></a>
<span id="l1337">    @Override</span><a href="#l1337"></a>
<span id="l1338">    HandshakeMessage getKickstartMessage() throws SSLException {</span><a href="#l1338"></a>
<span id="l1339">        // session ID of the ClientHello message</span><a href="#l1339"></a>
<span id="l1340">        SessionId sessionId = new SessionId(new byte[0]);</span><a href="#l1340"></a>
<span id="l1341"></span><a href="#l1341"></a>
<span id="l1342">        // a list of cipher suites sent by the client</span><a href="#l1342"></a>
<span id="l1343">        CipherSuiteList cipherSuites = getActiveCipherSuites();</span><a href="#l1343"></a>
<span id="l1344"></span><a href="#l1344"></a>
<span id="l1345">        // set the max protocol version this client is supporting.</span><a href="#l1345"></a>
<span id="l1346">        maxProtocolVersion = protocolVersion;</span><a href="#l1346"></a>
<span id="l1347"></span><a href="#l1347"></a>
<span id="l1348">        //</span><a href="#l1348"></a>
<span id="l1349">        // Try to resume an existing session.  This might be mandatory,</span><a href="#l1349"></a>
<span id="l1350">        // given certain API options.</span><a href="#l1350"></a>
<span id="l1351">        //</span><a href="#l1351"></a>
<span id="l1352">        session = ((SSLSessionContextImpl)sslContext</span><a href="#l1352"></a>
<span id="l1353">                        .engineGetClientSessionContext())</span><a href="#l1353"></a>
<span id="l1354">                        .get(getHostSE(), getPortSE());</span><a href="#l1354"></a>
<span id="l1355">        if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1355"></a>
<span id="l1356">            if (session != null) {</span><a href="#l1356"></a>
<span id="l1357">                System.out.println(&quot;%% Client cached &quot;</span><a href="#l1357"></a>
<span id="l1358">                    + session</span><a href="#l1358"></a>
<span id="l1359">                    + (session.isRejoinable() ? &quot;&quot; : &quot; (not rejoinable)&quot;));</span><a href="#l1359"></a>
<span id="l1360">            } else {</span><a href="#l1360"></a>
<span id="l1361">                System.out.println(&quot;%% No cached client session&quot;);</span><a href="#l1361"></a>
<span id="l1362">            }</span><a href="#l1362"></a>
<span id="l1363">        }</span><a href="#l1363"></a>
<span id="l1364">        if (session != null) {</span><a href="#l1364"></a>
<span id="l1365">            // If unsafe server certificate change is not allowed, reserve</span><a href="#l1365"></a>
<span id="l1366">            // current server certificates if the previous handshake is a</span><a href="#l1366"></a>
<span id="l1367">            // session-resumption abbreviated initial handshake.</span><a href="#l1367"></a>
<span id="l1368">            if (!allowUnsafeServerCertChange &amp;&amp; session.isSessionResumption()) {</span><a href="#l1368"></a>
<span id="l1369">                try {</span><a href="#l1369"></a>
<span id="l1370">                    // If existing, peer certificate chain cannot be null.</span><a href="#l1370"></a>
<span id="l1371">                    reservedServerCerts =</span><a href="#l1371"></a>
<span id="l1372">                        (X509Certificate[])session.getPeerCertificates();</span><a href="#l1372"></a>
<span id="l1373">                } catch (SSLPeerUnverifiedException puve) {</span><a href="#l1373"></a>
<span id="l1374">                    // Maybe not certificate-based, ignore the exception.</span><a href="#l1374"></a>
<span id="l1375">                }</span><a href="#l1375"></a>
<span id="l1376">            }</span><a href="#l1376"></a>
<span id="l1377"></span><a href="#l1377"></a>
<span id="l1378">            if (!session.isRejoinable()) {</span><a href="#l1378"></a>
<span id="l1379">                session = null;</span><a href="#l1379"></a>
<span id="l1380">            }</span><a href="#l1380"></a>
<span id="l1381">        }</span><a href="#l1381"></a>
<span id="l1382"></span><a href="#l1382"></a>
<span id="l1383">        if (session != null) {</span><a href="#l1383"></a>
<span id="l1384">            CipherSuite sessionSuite = session.getSuite();</span><a href="#l1384"></a>
<span id="l1385">            ProtocolVersion sessionVersion = session.getProtocolVersion();</span><a href="#l1385"></a>
<span id="l1386">            if (isNegotiable(sessionSuite) == false) {</span><a href="#l1386"></a>
<span id="l1387">                if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1387"></a>
<span id="l1388">                    System.out.println(&quot;%% can't resume, unavailable cipher&quot;);</span><a href="#l1388"></a>
<span id="l1389">                }</span><a href="#l1389"></a>
<span id="l1390">                session = null;</span><a href="#l1390"></a>
<span id="l1391">            }</span><a href="#l1391"></a>
<span id="l1392"></span><a href="#l1392"></a>
<span id="l1393">            if ((session != null) &amp;&amp; !isNegotiable(sessionVersion)) {</span><a href="#l1393"></a>
<span id="l1394">                if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1394"></a>
<span id="l1395">                    System.out.println(&quot;%% can't resume, protocol disabled&quot;);</span><a href="#l1395"></a>
<span id="l1396">                }</span><a href="#l1396"></a>
<span id="l1397">                session = null;</span><a href="#l1397"></a>
<span id="l1398">            }</span><a href="#l1398"></a>
<span id="l1399"></span><a href="#l1399"></a>
<span id="l1400">            if ((session != null) &amp;&amp; useExtendedMasterSecret) {</span><a href="#l1400"></a>
<span id="l1401">                boolean isTLS10Plus = sessionVersion.v &gt;= ProtocolVersion.TLS10.v;</span><a href="#l1401"></a>
<span id="l1402">                if (isTLS10Plus &amp;&amp; !session.getUseExtendedMasterSecret()) {</span><a href="#l1402"></a>
<span id="l1403">                    if (!allowLegacyResumption) {</span><a href="#l1403"></a>
<span id="l1404">                        // perform full handshake instead</span><a href="#l1404"></a>
<span id="l1405">                        //</span><a href="#l1405"></a>
<span id="l1406">                        // The client SHOULD NOT offer an abbreviated handshake</span><a href="#l1406"></a>
<span id="l1407">                        // to resume a session that does not use an extended</span><a href="#l1407"></a>
<span id="l1408">                        // master secret.  Instead, it SHOULD offer a full</span><a href="#l1408"></a>
<span id="l1409">                        // handshake.</span><a href="#l1409"></a>
<span id="l1410">                        session = null;</span><a href="#l1410"></a>
<span id="l1411">                    }</span><a href="#l1411"></a>
<span id="l1412">                }</span><a href="#l1412"></a>
<span id="l1413"></span><a href="#l1413"></a>
<span id="l1414">                if ((session != null) &amp;&amp; !allowUnsafeServerCertChange) {</span><a href="#l1414"></a>
<span id="l1415">                    // It is fine to move on with abbreviate handshake if</span><a href="#l1415"></a>
<span id="l1416">                    // endpoint identification is enabled.</span><a href="#l1416"></a>
<span id="l1417">                    String identityAlg = getEndpointIdentificationAlgorithmSE();</span><a href="#l1417"></a>
<span id="l1418">                    if ((identityAlg == null || identityAlg.length() == 0)) {</span><a href="#l1418"></a>
<span id="l1419">                        if (isTLS10Plus) {</span><a href="#l1419"></a>
<span id="l1420">                            if (!session.getUseExtendedMasterSecret()) {</span><a href="#l1420"></a>
<span id="l1421">                                // perform full handshake instead</span><a href="#l1421"></a>
<span id="l1422">                                session = null;</span><a href="#l1422"></a>
<span id="l1423">                            }   // Otherwise, use extended master secret.</span><a href="#l1423"></a>
<span id="l1424">                        } else {</span><a href="#l1424"></a>
<span id="l1425">                            // The extended master secret extension does not</span><a href="#l1425"></a>
<span id="l1426">                            // apply to SSL 3.0.  Perform a full handshake</span><a href="#l1426"></a>
<span id="l1427">                            // instead.</span><a href="#l1427"></a>
<span id="l1428">                            //</span><a href="#l1428"></a>
<span id="l1429">                            // Note that the useExtendedMasterSecret is</span><a href="#l1429"></a>
<span id="l1430">                            // extended to protect SSL 3.0 connections,</span><a href="#l1430"></a>
<span id="l1431">                            // by discarding abbreviate handshake.</span><a href="#l1431"></a>
<span id="l1432">                            session = null;</span><a href="#l1432"></a>
<span id="l1433">                        }</span><a href="#l1433"></a>
<span id="l1434">                    }</span><a href="#l1434"></a>
<span id="l1435">                }</span><a href="#l1435"></a>
<span id="l1436">            }</span><a href="#l1436"></a>
<span id="l1437"></span><a href="#l1437"></a>
<span id="l1438">            // ensure that the endpoint identification algorithm matches the</span><a href="#l1438"></a>
<span id="l1439">            // one in the session</span><a href="#l1439"></a>
<span id="l1440">            String identityAlg = getEndpointIdentificationAlgorithmSE();</span><a href="#l1440"></a>
<span id="l1441">            if (session != null &amp;&amp; identityAlg != null) {</span><a href="#l1441"></a>
<span id="l1442"></span><a href="#l1442"></a>
<span id="l1443">                String sessionIdentityAlg =</span><a href="#l1443"></a>
<span id="l1444">                    session.getEndpointIdentificationAlgorithm();</span><a href="#l1444"></a>
<span id="l1445">                if (!identityAlg.equalsIgnoreCase(sessionIdentityAlg)) {</span><a href="#l1445"></a>
<span id="l1446"></span><a href="#l1446"></a>
<span id="l1447">                    if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l1447"></a>
<span id="l1448">                        System.out.println(&quot;%% can't resume, endpoint id&quot; +</span><a href="#l1448"></a>
<span id="l1449">                            &quot; algorithm does not match, requested: &quot; +</span><a href="#l1449"></a>
<span id="l1450">                            identityAlg + &quot;, cached: &quot; + sessionIdentityAlg);</span><a href="#l1450"></a>
<span id="l1451">                    }</span><a href="#l1451"></a>
<span id="l1452">                    session = null;</span><a href="#l1452"></a>
<span id="l1453">                }</span><a href="#l1453"></a>
<span id="l1454">            }</span><a href="#l1454"></a>
<span id="l1455"></span><a href="#l1455"></a>
<span id="l1456">            if (session != null) {</span><a href="#l1456"></a>
<span id="l1457">                if (debug != null) {</span><a href="#l1457"></a>
<span id="l1458">                    if (Debug.isOn(&quot;handshake&quot;) || Debug.isOn(&quot;session&quot;)) {</span><a href="#l1458"></a>
<span id="l1459">                        System.out.println(&quot;%% Try resuming &quot; + session</span><a href="#l1459"></a>
<span id="l1460">                            + &quot; from port &quot; + getLocalPortSE());</span><a href="#l1460"></a>
<span id="l1461">                    }</span><a href="#l1461"></a>
<span id="l1462">                }</span><a href="#l1462"></a>
<span id="l1463"></span><a href="#l1463"></a>
<span id="l1464">                sessionId = session.getSessionId();</span><a href="#l1464"></a>
<span id="l1465">                maxProtocolVersion = sessionVersion;</span><a href="#l1465"></a>
<span id="l1466"></span><a href="#l1466"></a>
<span id="l1467">                // Update SSL version number in underlying SSL socket and</span><a href="#l1467"></a>
<span id="l1468">                // handshake output stream, so that the output records (at the</span><a href="#l1468"></a>
<span id="l1469">                // record layer) have the correct version</span><a href="#l1469"></a>
<span id="l1470">                setVersion(sessionVersion);</span><a href="#l1470"></a>
<span id="l1471">            }</span><a href="#l1471"></a>
<span id="l1472"></span><a href="#l1472"></a>
<span id="l1473">            /*</span><a href="#l1473"></a>
<span id="l1474">             * Force use of the previous session ciphersuite, and</span><a href="#l1474"></a>
<span id="l1475">             * add the SCSV if enabled.</span><a href="#l1475"></a>
<span id="l1476">             */</span><a href="#l1476"></a>
<span id="l1477">            if (!enableNewSession) {</span><a href="#l1477"></a>
<span id="l1478">                if (session == null) {</span><a href="#l1478"></a>
<span id="l1479">                    throw new SSLHandshakeException(</span><a href="#l1479"></a>
<span id="l1480">                        &quot;Can't reuse existing SSL client session&quot;);</span><a href="#l1480"></a>
<span id="l1481">                }</span><a href="#l1481"></a>
<span id="l1482"></span><a href="#l1482"></a>
<span id="l1483">                Collection&lt;CipherSuite&gt; cipherList = new ArrayList&lt;&gt;(2);</span><a href="#l1483"></a>
<span id="l1484">                cipherList.add(sessionSuite);</span><a href="#l1484"></a>
<span id="l1485">                if (!secureRenegotiation &amp;&amp;</span><a href="#l1485"></a>
<span id="l1486">                        cipherSuites.contains(CipherSuite.C_SCSV)) {</span><a href="#l1486"></a>
<span id="l1487">                    cipherList.add(CipherSuite.C_SCSV);</span><a href="#l1487"></a>
<span id="l1488">                }   // otherwise, renegotiation_info extension will be used</span><a href="#l1488"></a>
<span id="l1489"></span><a href="#l1489"></a>
<span id="l1490">                cipherSuites = new CipherSuiteList(cipherList);</span><a href="#l1490"></a>
<span id="l1491">            }</span><a href="#l1491"></a>
<span id="l1492">        }</span><a href="#l1492"></a>
<span id="l1493"></span><a href="#l1493"></a>
<span id="l1494">        if (session == null &amp;&amp; !enableNewSession) {</span><a href="#l1494"></a>
<span id="l1495">            throw new SSLHandshakeException(&quot;No existing session to resume&quot;);</span><a href="#l1495"></a>
<span id="l1496">        }</span><a href="#l1496"></a>
<span id="l1497"></span><a href="#l1497"></a>
<span id="l1498">        // exclude SCSV for secure renegotiation</span><a href="#l1498"></a>
<span id="l1499">        if (secureRenegotiation &amp;&amp; cipherSuites.contains(CipherSuite.C_SCSV)) {</span><a href="#l1499"></a>
<span id="l1500">            Collection&lt;CipherSuite&gt; cipherList =</span><a href="#l1500"></a>
<span id="l1501">                        new ArrayList&lt;&gt;(cipherSuites.size() - 1);</span><a href="#l1501"></a>
<span id="l1502">            for (CipherSuite suite : cipherSuites.collection()) {</span><a href="#l1502"></a>
<span id="l1503">                if (suite != CipherSuite.C_SCSV) {</span><a href="#l1503"></a>
<span id="l1504">                    cipherList.add(suite);</span><a href="#l1504"></a>
<span id="l1505">                }</span><a href="#l1505"></a>
<span id="l1506">            }</span><a href="#l1506"></a>
<span id="l1507"></span><a href="#l1507"></a>
<span id="l1508">            cipherSuites = new CipherSuiteList(cipherList);</span><a href="#l1508"></a>
<span id="l1509">        }</span><a href="#l1509"></a>
<span id="l1510"></span><a href="#l1510"></a>
<span id="l1511">        // make sure there is a negotiable cipher suite.</span><a href="#l1511"></a>
<span id="l1512">        boolean negotiable = false;</span><a href="#l1512"></a>
<span id="l1513">        for (CipherSuite suite : cipherSuites.collection()) {</span><a href="#l1513"></a>
<span id="l1514">            if (isNegotiable(suite)) {</span><a href="#l1514"></a>
<span id="l1515">                negotiable = true;</span><a href="#l1515"></a>
<span id="l1516">                break;</span><a href="#l1516"></a>
<span id="l1517">            }</span><a href="#l1517"></a>
<span id="l1518">        }</span><a href="#l1518"></a>
<span id="l1519"></span><a href="#l1519"></a>
<span id="l1520">        if (!negotiable) {</span><a href="#l1520"></a>
<span id="l1521">            throw new SSLHandshakeException(&quot;No negotiable cipher suite&quot;);</span><a href="#l1521"></a>
<span id="l1522">        }</span><a href="#l1522"></a>
<span id="l1523"></span><a href="#l1523"></a>
<span id="l1524">        // Not a TLS1.2+ handshake</span><a href="#l1524"></a>
<span id="l1525">        // For SSLv2Hello, HandshakeHash.reset() will be called, so we</span><a href="#l1525"></a>
<span id="l1526">        // cannot call HandshakeHash.protocolDetermined() here. As it does</span><a href="#l1526"></a>
<span id="l1527">        // not follow the spec that HandshakeHash.reset() can be only be</span><a href="#l1527"></a>
<span id="l1528">        // called before protocolDetermined.</span><a href="#l1528"></a>
<span id="l1529">        // if (maxProtocolVersion.v &lt; ProtocolVersion.TLS12.v) {</span><a href="#l1529"></a>
<span id="l1530">        //     handshakeHash.protocolDetermined(maxProtocolVersion);</span><a href="#l1530"></a>
<span id="l1531">        // }</span><a href="#l1531"></a>
<span id="l1532"></span><a href="#l1532"></a>
<span id="l1533">        // create the ClientHello message</span><a href="#l1533"></a>
<span id="l1534">        ClientHello clientHelloMessage = new ClientHello(</span><a href="#l1534"></a>
<span id="l1535">                sslContext.getSecureRandom(), maxProtocolVersion,</span><a href="#l1535"></a>
<span id="l1536">                sessionId, cipherSuites);</span><a href="#l1536"></a>
<span id="l1537"></span><a href="#l1537"></a>
<span id="l1538">        // add elliptic curves and point format extensions</span><a href="#l1538"></a>
<span id="l1539">        if (cipherSuites.containsEC()) {</span><a href="#l1539"></a>
<span id="l1540">            EllipticCurvesExtension ece =</span><a href="#l1540"></a>
<span id="l1541">                EllipticCurvesExtension.createExtension(algorithmConstraints);</span><a href="#l1541"></a>
<span id="l1542">            if (ece != null) {</span><a href="#l1542"></a>
<span id="l1543">                clientHelloMessage.extensions.add(ece);</span><a href="#l1543"></a>
<span id="l1544">                clientHelloMessage.extensions.add(</span><a href="#l1544"></a>
<span id="l1545">                   EllipticPointFormatsExtension.DEFAULT);</span><a href="#l1545"></a>
<span id="l1546">            }</span><a href="#l1546"></a>
<span id="l1547">        }</span><a href="#l1547"></a>
<span id="l1548"></span><a href="#l1548"></a>
<span id="l1549">        // add signature_algorithm extension</span><a href="#l1549"></a>
<span id="l1550">        if (maxProtocolVersion.v &gt;= ProtocolVersion.TLS12.v) {</span><a href="#l1550"></a>
<span id="l1551">            // we will always send the signature_algorithm extension</span><a href="#l1551"></a>
<span id="l1552">            Collection&lt;SignatureAndHashAlgorithm&gt; localSignAlgs =</span><a href="#l1552"></a>
<span id="l1553">                                                getLocalSupportedSignAlgs();</span><a href="#l1553"></a>
<span id="l1554">            if (localSignAlgs.isEmpty()) {</span><a href="#l1554"></a>
<span id="l1555">                throw new SSLHandshakeException(</span><a href="#l1555"></a>
<span id="l1556">                            &quot;No supported signature algorithm&quot;);</span><a href="#l1556"></a>
<span id="l1557">            }</span><a href="#l1557"></a>
<span id="l1558"></span><a href="#l1558"></a>
<span id="l1559">            clientHelloMessage.addSignatureAlgorithmsExtension(localSignAlgs);</span><a href="#l1559"></a>
<span id="l1560">        }</span><a href="#l1560"></a>
<span id="l1561"></span><a href="#l1561"></a>
<span id="l1562">        // add Extended Master Secret extension</span><a href="#l1562"></a>
<span id="l1563">        if (useExtendedMasterSecret &amp;&amp; (maxProtocolVersion.v &gt;= ProtocolVersion.TLS10.v)) {</span><a href="#l1563"></a>
<span id="l1564">            if ((session == null) || session.getUseExtendedMasterSecret()) {</span><a href="#l1564"></a>
<span id="l1565">                clientHelloMessage.addExtendedMasterSecretExtension();</span><a href="#l1565"></a>
<span id="l1566">                requestedToUseEMS = true;</span><a href="#l1566"></a>
<span id="l1567">            }</span><a href="#l1567"></a>
<span id="l1568">        }</span><a href="#l1568"></a>
<span id="l1569"></span><a href="#l1569"></a>
<span id="l1570">        // add server_name extension</span><a href="#l1570"></a>
<span id="l1571">        if (enableSNIExtension) {</span><a href="#l1571"></a>
<span id="l1572">            if (session != null) {</span><a href="#l1572"></a>
<span id="l1573">                requestedServerNames = session.getRequestedServerNames();</span><a href="#l1573"></a>
<span id="l1574">            } else {</span><a href="#l1574"></a>
<span id="l1575">                requestedServerNames = serverNames;</span><a href="#l1575"></a>
<span id="l1576">            }</span><a href="#l1576"></a>
<span id="l1577"></span><a href="#l1577"></a>
<span id="l1578">            if (!requestedServerNames.isEmpty()) {</span><a href="#l1578"></a>
<span id="l1579">                clientHelloMessage.addSNIExtension(requestedServerNames);</span><a href="#l1579"></a>
<span id="l1580">            }</span><a href="#l1580"></a>
<span id="l1581">        }</span><a href="#l1581"></a>
<span id="l1582"></span><a href="#l1582"></a>
<span id="l1583">        // Add ALPN extension</span><a href="#l1583"></a>
<span id="l1584">        if (localApl != null &amp;&amp; localApl.length &gt; 0) {</span><a href="#l1584"></a>
<span id="l1585">            clientHelloMessage.addALPNExtension(localApl);</span><a href="#l1585"></a>
<span id="l1586">            alpnActive = true;</span><a href="#l1586"></a>
<span id="l1587">        }</span><a href="#l1587"></a>
<span id="l1588"></span><a href="#l1588"></a>
<span id="l1589">        // reset the client random cookie</span><a href="#l1589"></a>
<span id="l1590">        clnt_random = clientHelloMessage.clnt_random;</span><a href="#l1590"></a>
<span id="l1591"></span><a href="#l1591"></a>
<span id="l1592">        /*</span><a href="#l1592"></a>
<span id="l1593">         * need to set the renegotiation_info extension for:</span><a href="#l1593"></a>
<span id="l1594">         * 1: secure renegotiation</span><a href="#l1594"></a>
<span id="l1595">         * 2: initial handshake and no SCSV in the ClientHello</span><a href="#l1595"></a>
<span id="l1596">         * 3: insecure renegotiation and no SCSV in the ClientHello</span><a href="#l1596"></a>
<span id="l1597">         */</span><a href="#l1597"></a>
<span id="l1598">        if (secureRenegotiation ||</span><a href="#l1598"></a>
<span id="l1599">                !cipherSuites.contains(CipherSuite.C_SCSV)) {</span><a href="#l1599"></a>
<span id="l1600">            clientHelloMessage.addRenegotiationInfoExtension(clientVerifyData);</span><a href="#l1600"></a>
<span id="l1601">        }</span><a href="#l1601"></a>
<span id="l1602"></span><a href="#l1602"></a>
<span id="l1603">        return clientHelloMessage;</span><a href="#l1603"></a>
<span id="l1604">    }</span><a href="#l1604"></a>
<span id="l1605"></span><a href="#l1605"></a>
<span id="l1606">    /*</span><a href="#l1606"></a>
<span id="l1607">     * Fault detected during handshake.</span><a href="#l1607"></a>
<span id="l1608">     */</span><a href="#l1608"></a>
<span id="l1609">    @Override</span><a href="#l1609"></a>
<span id="l1610">    void handshakeAlert(byte description) throws SSLProtocolException {</span><a href="#l1610"></a>
<span id="l1611">        String message = Alerts.alertDescription(description);</span><a href="#l1611"></a>
<span id="l1612"></span><a href="#l1612"></a>
<span id="l1613">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1613"></a>
<span id="l1614">            System.out.println(&quot;SSL - handshake alert: &quot; + message);</span><a href="#l1614"></a>
<span id="l1615">        }</span><a href="#l1615"></a>
<span id="l1616">        throw new SSLProtocolException(&quot;handshake alert:  &quot; + message);</span><a href="#l1616"></a>
<span id="l1617">    }</span><a href="#l1617"></a>
<span id="l1618"></span><a href="#l1618"></a>
<span id="l1619">    /*</span><a href="#l1619"></a>
<span id="l1620">     * Unless we are using an anonymous ciphersuite, the server always</span><a href="#l1620"></a>
<span id="l1621">     * sends a certificate message (for the CipherSuites we currently</span><a href="#l1621"></a>
<span id="l1622">     * support). The trust manager verifies the chain for us.</span><a href="#l1622"></a>
<span id="l1623">     */</span><a href="#l1623"></a>
<span id="l1624">    private void serverCertificate(CertificateMsg mesg) throws IOException {</span><a href="#l1624"></a>
<span id="l1625">        if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1625"></a>
<span id="l1626">            mesg.print(System.out);</span><a href="#l1626"></a>
<span id="l1627">        }</span><a href="#l1627"></a>
<span id="l1628">        X509Certificate[] peerCerts = mesg.getCertificateChain();</span><a href="#l1628"></a>
<span id="l1629">        if (peerCerts.length == 0) {</span><a href="#l1629"></a>
<span id="l1630">            fatalSE(Alerts.alert_bad_certificate, &quot;empty certificate chain&quot;);</span><a href="#l1630"></a>
<span id="l1631">        }</span><a href="#l1631"></a>
<span id="l1632"></span><a href="#l1632"></a>
<span id="l1633">        // Allow server certificate change in client side during renegotiation</span><a href="#l1633"></a>
<span id="l1634">        // after a session-resumption abbreviated initial handshake?</span><a href="#l1634"></a>
<span id="l1635">        //</span><a href="#l1635"></a>
<span id="l1636">        // DO NOT need to check allowUnsafeServerCertChange here.  We only</span><a href="#l1636"></a>
<span id="l1637">        // reserve server certificates when allowUnsafeServerCertChange is</span><a href="#l1637"></a>
<span id="l1638">        // flase.</span><a href="#l1638"></a>
<span id="l1639">        //</span><a href="#l1639"></a>
<span id="l1640">        // Allow server certificate change if it is negotiated to use the</span><a href="#l1640"></a>
<span id="l1641">        // extended master secret.</span><a href="#l1641"></a>
<span id="l1642">        if ((reservedServerCerts != null) &amp;&amp;</span><a href="#l1642"></a>
<span id="l1643">                !session.getUseExtendedMasterSecret()) {</span><a href="#l1643"></a>
<span id="l1644">            // It is not necessary to check the certificate update if endpoint</span><a href="#l1644"></a>
<span id="l1645">            // identification is enabled.</span><a href="#l1645"></a>
<span id="l1646">            String identityAlg = getEndpointIdentificationAlgorithmSE();</span><a href="#l1646"></a>
<span id="l1647">            if ((identityAlg == null || identityAlg.length() == 0) &amp;&amp;</span><a href="#l1647"></a>
<span id="l1648">                !isIdentityEquivalent(peerCerts[0], reservedServerCerts[0])) {</span><a href="#l1648"></a>
<span id="l1649"></span><a href="#l1649"></a>
<span id="l1650">                fatalSE(Alerts.alert_bad_certificate,</span><a href="#l1650"></a>
<span id="l1651">                        &quot;server certificate change is restricted &quot; +</span><a href="#l1651"></a>
<span id="l1652">                        &quot;during renegotiation&quot;);</span><a href="#l1652"></a>
<span id="l1653">            }</span><a href="#l1653"></a>
<span id="l1654">        }</span><a href="#l1654"></a>
<span id="l1655"></span><a href="#l1655"></a>
<span id="l1656">        // ask the trust manager to verify the chain</span><a href="#l1656"></a>
<span id="l1657">        X509TrustManager tm = sslContext.getX509TrustManager();</span><a href="#l1657"></a>
<span id="l1658">        try {</span><a href="#l1658"></a>
<span id="l1659">            // find out the key exchange algorithm used</span><a href="#l1659"></a>
<span id="l1660">            // use &quot;RSA&quot; for non-ephemeral &quot;RSA_EXPORT&quot;</span><a href="#l1660"></a>
<span id="l1661">            String keyExchangeString;</span><a href="#l1661"></a>
<span id="l1662">            if (keyExchange == K_RSA_EXPORT &amp;&amp; !serverKeyExchangeReceived) {</span><a href="#l1662"></a>
<span id="l1663">                keyExchangeString = K_RSA.name;</span><a href="#l1663"></a>
<span id="l1664">            } else {</span><a href="#l1664"></a>
<span id="l1665">                keyExchangeString = keyExchange.name;</span><a href="#l1665"></a>
<span id="l1666">            }</span><a href="#l1666"></a>
<span id="l1667"></span><a href="#l1667"></a>
<span id="l1668">            if (tm instanceof X509ExtendedTrustManager) {</span><a href="#l1668"></a>
<span id="l1669">                if (conn != null) {</span><a href="#l1669"></a>
<span id="l1670">                    ((X509ExtendedTrustManager)tm).checkServerTrusted(</span><a href="#l1670"></a>
<span id="l1671">                        peerCerts.clone(),</span><a href="#l1671"></a>
<span id="l1672">                        keyExchangeString,</span><a href="#l1672"></a>
<span id="l1673">                        conn);</span><a href="#l1673"></a>
<span id="l1674">                } else {</span><a href="#l1674"></a>
<span id="l1675">                    ((X509ExtendedTrustManager)tm).checkServerTrusted(</span><a href="#l1675"></a>
<span id="l1676">                        peerCerts.clone(),</span><a href="#l1676"></a>
<span id="l1677">                        keyExchangeString,</span><a href="#l1677"></a>
<span id="l1678">                        engine);</span><a href="#l1678"></a>
<span id="l1679">                }</span><a href="#l1679"></a>
<span id="l1680">            } else {</span><a href="#l1680"></a>
<span id="l1681">                // Unlikely to happen, because we have wrapped the old</span><a href="#l1681"></a>
<span id="l1682">                // X509TrustManager with the new X509ExtendedTrustManager.</span><a href="#l1682"></a>
<span id="l1683">                throw new CertificateException(</span><a href="#l1683"></a>
<span id="l1684">                    &quot;Improper X509TrustManager implementation&quot;);</span><a href="#l1684"></a>
<span id="l1685">            }</span><a href="#l1685"></a>
<span id="l1686">        } catch (CertificateException e) {</span><a href="#l1686"></a>
<span id="l1687">            // This will throw an exception, so include the original error.</span><a href="#l1687"></a>
<span id="l1688">            fatalSE(Alerts.alert_certificate_unknown, e);</span><a href="#l1688"></a>
<span id="l1689">        }</span><a href="#l1689"></a>
<span id="l1690">        session.setPeerCertificates(peerCerts);</span><a href="#l1690"></a>
<span id="l1691">    }</span><a href="#l1691"></a>
<span id="l1692"></span><a href="#l1692"></a>
<span id="l1693">    /*</span><a href="#l1693"></a>
<span id="l1694">     * Whether the certificates can represent the same identity?</span><a href="#l1694"></a>
<span id="l1695">     *</span><a href="#l1695"></a>
<span id="l1696">     * The certificates can be used to represent the same identity:</span><a href="#l1696"></a>
<span id="l1697">     *     1. If the subject alternative names of IP address are present in</span><a href="#l1697"></a>
<span id="l1698">     *        both certificates, they should be identical; otherwise,</span><a href="#l1698"></a>
<span id="l1699">     *     2. if the subject alternative names of DNS name are present in</span><a href="#l1699"></a>
<span id="l1700">     *        both certificates, they should be identical; otherwise,</span><a href="#l1700"></a>
<span id="l1701">     *     3. if the subject fields are present in both certificates, the</span><a href="#l1701"></a>
<span id="l1702">     *        certificate subjects and issuers should be identical.</span><a href="#l1702"></a>
<span id="l1703">     */</span><a href="#l1703"></a>
<span id="l1704">    private static boolean isIdentityEquivalent(X509Certificate thisCert,</span><a href="#l1704"></a>
<span id="l1705">            X509Certificate prevCert) {</span><a href="#l1705"></a>
<span id="l1706">        if (thisCert.equals(prevCert)) {</span><a href="#l1706"></a>
<span id="l1707">            return true;</span><a href="#l1707"></a>
<span id="l1708">        }</span><a href="#l1708"></a>
<span id="l1709"></span><a href="#l1709"></a>
<span id="l1710">        // check subject alternative names</span><a href="#l1710"></a>
<span id="l1711">        Collection&lt;List&lt;?&gt;&gt; thisSubjectAltNames = null;</span><a href="#l1711"></a>
<span id="l1712">        try {</span><a href="#l1712"></a>
<span id="l1713">            thisSubjectAltNames = thisCert.getSubjectAlternativeNames();</span><a href="#l1713"></a>
<span id="l1714">        } catch (CertificateParsingException cpe) {</span><a href="#l1714"></a>
<span id="l1715">            if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1715"></a>
<span id="l1716">                System.out.println(</span><a href="#l1716"></a>
<span id="l1717">                        &quot;Attempt to obtain subjectAltNames extension failed!&quot;);</span><a href="#l1717"></a>
<span id="l1718">            }</span><a href="#l1718"></a>
<span id="l1719">        }</span><a href="#l1719"></a>
<span id="l1720"></span><a href="#l1720"></a>
<span id="l1721">        Collection&lt;List&lt;?&gt;&gt; prevSubjectAltNames = null;</span><a href="#l1721"></a>
<span id="l1722">        try {</span><a href="#l1722"></a>
<span id="l1723">            prevSubjectAltNames = prevCert.getSubjectAlternativeNames();</span><a href="#l1723"></a>
<span id="l1724">        } catch (CertificateParsingException cpe) {</span><a href="#l1724"></a>
<span id="l1725">            if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1725"></a>
<span id="l1726">                System.out.println(</span><a href="#l1726"></a>
<span id="l1727">                        &quot;Attempt to obtain subjectAltNames extension failed!&quot;);</span><a href="#l1727"></a>
<span id="l1728">            }</span><a href="#l1728"></a>
<span id="l1729">        }</span><a href="#l1729"></a>
<span id="l1730"></span><a href="#l1730"></a>
<span id="l1731">        if ((thisSubjectAltNames != null) &amp;&amp; (prevSubjectAltNames != null)) {</span><a href="#l1731"></a>
<span id="l1732">            // check the iPAddress field in subjectAltName extension</span><a href="#l1732"></a>
<span id="l1733">            Collection&lt;String&gt; thisSubAltIPAddrs =</span><a href="#l1733"></a>
<span id="l1734">                        getSubjectAltNames(thisSubjectAltNames, ALTNAME_IP);</span><a href="#l1734"></a>
<span id="l1735">            Collection&lt;String&gt; prevSubAltIPAddrs =</span><a href="#l1735"></a>
<span id="l1736">                        getSubjectAltNames(prevSubjectAltNames, ALTNAME_IP);</span><a href="#l1736"></a>
<span id="l1737">            if ((thisSubAltIPAddrs != null) &amp;&amp; (prevSubAltIPAddrs != null) &amp;&amp;</span><a href="#l1737"></a>
<span id="l1738">                (isEquivalent(thisSubAltIPAddrs, prevSubAltIPAddrs))) {</span><a href="#l1738"></a>
<span id="l1739"></span><a href="#l1739"></a>
<span id="l1740">                return true;</span><a href="#l1740"></a>
<span id="l1741">            }</span><a href="#l1741"></a>
<span id="l1742"></span><a href="#l1742"></a>
<span id="l1743">            // check the dNSName field in subjectAltName extension</span><a href="#l1743"></a>
<span id="l1744">            Collection&lt;String&gt; thisSubAltDnsNames =</span><a href="#l1744"></a>
<span id="l1745">                        getSubjectAltNames(thisSubjectAltNames, ALTNAME_DNS);</span><a href="#l1745"></a>
<span id="l1746">            Collection&lt;String&gt; prevSubAltDnsNames =</span><a href="#l1746"></a>
<span id="l1747">                        getSubjectAltNames(prevSubjectAltNames, ALTNAME_DNS);</span><a href="#l1747"></a>
<span id="l1748">            if ((thisSubAltDnsNames != null) &amp;&amp; (prevSubAltDnsNames != null) &amp;&amp;</span><a href="#l1748"></a>
<span id="l1749">                (isEquivalent(thisSubAltDnsNames, prevSubAltDnsNames))) {</span><a href="#l1749"></a>
<span id="l1750"></span><a href="#l1750"></a>
<span id="l1751">                return true;</span><a href="#l1751"></a>
<span id="l1752">            }</span><a href="#l1752"></a>
<span id="l1753">        }</span><a href="#l1753"></a>
<span id="l1754"></span><a href="#l1754"></a>
<span id="l1755">        // check the certificate subject and issuer</span><a href="#l1755"></a>
<span id="l1756">        X500Principal thisSubject = thisCert.getSubjectX500Principal();</span><a href="#l1756"></a>
<span id="l1757">        X500Principal prevSubject = prevCert.getSubjectX500Principal();</span><a href="#l1757"></a>
<span id="l1758">        X500Principal thisIssuer = thisCert.getIssuerX500Principal();</span><a href="#l1758"></a>
<span id="l1759">        X500Principal prevIssuer = prevCert.getIssuerX500Principal();</span><a href="#l1759"></a>
<span id="l1760">        if (!thisSubject.getName().isEmpty() &amp;&amp;</span><a href="#l1760"></a>
<span id="l1761">                !prevSubject.getName().isEmpty() &amp;&amp;</span><a href="#l1761"></a>
<span id="l1762">                thisSubject.equals(prevSubject) &amp;&amp;</span><a href="#l1762"></a>
<span id="l1763">                thisIssuer.equals(prevIssuer)) {</span><a href="#l1763"></a>
<span id="l1764">            return true;</span><a href="#l1764"></a>
<span id="l1765">        }</span><a href="#l1765"></a>
<span id="l1766"></span><a href="#l1766"></a>
<span id="l1767">        return false;</span><a href="#l1767"></a>
<span id="l1768">    }</span><a href="#l1768"></a>
<span id="l1769"></span><a href="#l1769"></a>
<span id="l1770">    /*</span><a href="#l1770"></a>
<span id="l1771">     * Returns the subject alternative name of the specified type in the</span><a href="#l1771"></a>
<span id="l1772">     * subjectAltNames extension of a certificate.</span><a href="#l1772"></a>
<span id="l1773">     *</span><a href="#l1773"></a>
<span id="l1774">     * Note that only those subjectAltName types that use String data</span><a href="#l1774"></a>
<span id="l1775">     * should be passed into this function.</span><a href="#l1775"></a>
<span id="l1776">     */</span><a href="#l1776"></a>
<span id="l1777">    private static Collection&lt;String&gt; getSubjectAltNames(</span><a href="#l1777"></a>
<span id="l1778">            Collection&lt;List&lt;?&gt;&gt; subjectAltNames, int type) {</span><a href="#l1778"></a>
<span id="l1779"></span><a href="#l1779"></a>
<span id="l1780">        HashSet&lt;String&gt; subAltDnsNames = null;</span><a href="#l1780"></a>
<span id="l1781">        for (List&lt;?&gt; subjectAltName : subjectAltNames) {</span><a href="#l1781"></a>
<span id="l1782">            int subjectAltNameType = (Integer)subjectAltName.get(0);</span><a href="#l1782"></a>
<span id="l1783">            if (subjectAltNameType == type) {</span><a href="#l1783"></a>
<span id="l1784">                String subAltDnsName = (String)subjectAltName.get(1);</span><a href="#l1784"></a>
<span id="l1785">                if ((subAltDnsName != null) &amp;&amp; !subAltDnsName.isEmpty()) {</span><a href="#l1785"></a>
<span id="l1786">                    if (subAltDnsNames == null) {</span><a href="#l1786"></a>
<span id="l1787">                        subAltDnsNames =</span><a href="#l1787"></a>
<span id="l1788">                                new HashSet&lt;&gt;(subjectAltNames.size());</span><a href="#l1788"></a>
<span id="l1789">                    }</span><a href="#l1789"></a>
<span id="l1790">                    subAltDnsNames.add(subAltDnsName);</span><a href="#l1790"></a>
<span id="l1791">                }</span><a href="#l1791"></a>
<span id="l1792">            }</span><a href="#l1792"></a>
<span id="l1793">        }</span><a href="#l1793"></a>
<span id="l1794"></span><a href="#l1794"></a>
<span id="l1795">        return subAltDnsNames;</span><a href="#l1795"></a>
<span id="l1796">    }</span><a href="#l1796"></a>
<span id="l1797"></span><a href="#l1797"></a>
<span id="l1798">    private static boolean isEquivalent(Collection&lt;String&gt; thisSubAltNames,</span><a href="#l1798"></a>
<span id="l1799">            Collection&lt;String&gt; prevSubAltNames) {</span><a href="#l1799"></a>
<span id="l1800"></span><a href="#l1800"></a>
<span id="l1801">        for (String thisSubAltName : thisSubAltNames) {</span><a href="#l1801"></a>
<span id="l1802">            for (String prevSubAltName : prevSubAltNames) {</span><a href="#l1802"></a>
<span id="l1803">                // Only allow the exactly match.  Check no wildcard character.</span><a href="#l1803"></a>
<span id="l1804">                if (thisSubAltName.equalsIgnoreCase(prevSubAltName)) {</span><a href="#l1804"></a>
<span id="l1805">                    return true;</span><a href="#l1805"></a>
<span id="l1806">                }</span><a href="#l1806"></a>
<span id="l1807">            }</span><a href="#l1807"></a>
<span id="l1808">        }</span><a href="#l1808"></a>
<span id="l1809"></span><a href="#l1809"></a>
<span id="l1810">        return false;</span><a href="#l1810"></a>
<span id="l1811">    }</span><a href="#l1811"></a>
<span id="l1812">}</span><a href="#l1812"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

