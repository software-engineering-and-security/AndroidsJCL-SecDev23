<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: fe2a830bf68a src/share/classes/sun/security/ssl/SSLEngineImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/fe2a830bf68a">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/fe2a830bf68a">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/fe2a830bf68a">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/fe2a830bf68a/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/security/ssl/SSLEngineImpl.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLEngineImpl.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLEngineImpl.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLEngineImpl.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLEngineImpl.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLEngineImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/SSLEngineImpl.java @ 9001:fe2a830bf68a</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
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
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/1f10820808c5/src/share/classes/sun/security/ssl/SSLEngineImpl.java">1f10820808c5</a> </td>
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
<span id="l2"> * Copyright (c) 2003, 2014, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l29">import java.nio.*;</span><a href="#l29"></a>
<span id="l30">import java.security.*;</span><a href="#l30"></a>
<span id="l31"></span><a href="#l31"></a>
<span id="l32">import javax.crypto.BadPaddingException;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">import javax.net.ssl.*;</span><a href="#l34"></a>
<span id="l35">import javax.net.ssl.SSLEngineResult.*;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * Implementation of an non-blocking SSLEngine.</span><a href="#l39"></a>
<span id="l40"> *</span><a href="#l40"></a>
<span id="l41"> * *Currently*, the SSLEngine code exists in parallel with the current</span><a href="#l41"></a>
<span id="l42"> * SSLSocket.  As such, the current implementation is using legacy code</span><a href="#l42"></a>
<span id="l43"> * with many of the same abstractions.  However, it varies in many</span><a href="#l43"></a>
<span id="l44"> * areas, most dramatically in the IO handling.</span><a href="#l44"></a>
<span id="l45"> *</span><a href="#l45"></a>
<span id="l46"> * There are three main I/O threads that can be existing in parallel:</span><a href="#l46"></a>
<span id="l47"> * wrap(), unwrap(), and beginHandshake().  We are encouraging users to</span><a href="#l47"></a>
<span id="l48"> * not call multiple instances of wrap or unwrap, because the data could</span><a href="#l48"></a>
<span id="l49"> * appear to flow out of the SSLEngine in a non-sequential order.  We</span><a href="#l49"></a>
<span id="l50"> * take all steps we can to at least make sure the ordering remains</span><a href="#l50"></a>
<span id="l51"> * consistent, but once the calls returns, anything can happen.  For</span><a href="#l51"></a>
<span id="l52"> * example, thread1 and thread2 both call wrap, thread1 gets the first</span><a href="#l52"></a>
<span id="l53"> * packet, thread2 gets the second packet, but thread2 gets control back</span><a href="#l53"></a>
<span id="l54"> * before thread1, and sends the data.  The receiving side would see an</span><a href="#l54"></a>
<span id="l55"> * out-of-order error.</span><a href="#l55"></a>
<span id="l56"> *</span><a href="#l56"></a>
<span id="l57"> * Handshaking is still done the same way as SSLSocket using the normal</span><a href="#l57"></a>
<span id="l58"> * InputStream/OutputStream abstactions.  We create</span><a href="#l58"></a>
<span id="l59"> * ClientHandshakers/ServerHandshakers, which produce/consume the</span><a href="#l59"></a>
<span id="l60"> * handshaking data.  The transfer of the data is largely handled by the</span><a href="#l60"></a>
<span id="l61"> * HandshakeInStream/HandshakeOutStreams.  Lastly, the</span><a href="#l61"></a>
<span id="l62"> * InputRecord/OutputRecords still have the same functionality, except</span><a href="#l62"></a>
<span id="l63"> * that they are overridden with EngineInputRecord/EngineOutputRecord,</span><a href="#l63"></a>
<span id="l64"> * which provide SSLEngine-specific functionality.</span><a href="#l64"></a>
<span id="l65"> *</span><a href="#l65"></a>
<span id="l66"> * Some of the major differences are:</span><a href="#l66"></a>
<span id="l67"> *</span><a href="#l67"></a>
<span id="l68"> * EngineInputRecord/EngineOutputRecord/EngineWriter:</span><a href="#l68"></a>
<span id="l69"> *</span><a href="#l69"></a>
<span id="l70"> *      In order to avoid writing whole new control flows for</span><a href="#l70"></a>
<span id="l71"> *      handshaking, and to reuse most of the same code, we kept most</span><a href="#l71"></a>
<span id="l72"> *      of the actual handshake code the same.  As usual, reading</span><a href="#l72"></a>
<span id="l73"> *      handshake data may trigger output of more handshake data, so</span><a href="#l73"></a>
<span id="l74"> *      what we do is write this data to internal buffers, and wait for</span><a href="#l74"></a>
<span id="l75"> *      wrap() to be called to give that data a ride.</span><a href="#l75"></a>
<span id="l76"> *</span><a href="#l76"></a>
<span id="l77"> *      All data is routed through</span><a href="#l77"></a>
<span id="l78"> *      EngineInputRecord/EngineOutputRecord.  However, all handshake</span><a href="#l78"></a>
<span id="l79"> *      data (ct_alert/ct_change_cipher_spec/ct_handshake) are passed</span><a href="#l79"></a>
<span id="l80"> *      through to the the underlying InputRecord/OutputRecord, and</span><a href="#l80"></a>
<span id="l81"> *      the data uses the internal buffers.</span><a href="#l81"></a>
<span id="l82"> *</span><a href="#l82"></a>
<span id="l83"> *      Application data is handled slightly different, we copy the data</span><a href="#l83"></a>
<span id="l84"> *      directly from the src to the dst buffers, and do all operations</span><a href="#l84"></a>
<span id="l85"> *      on those buffers, saving the overhead of multiple copies.</span><a href="#l85"></a>
<span id="l86"> *</span><a href="#l86"></a>
<span id="l87"> *      In the case of an inbound record, unwrap passes the inbound</span><a href="#l87"></a>
<span id="l88"> *      ByteBuffer to the InputRecord.  If the data is handshake data,</span><a href="#l88"></a>
<span id="l89"> *      the data is read into the InputRecord's internal buffer.  If</span><a href="#l89"></a>
<span id="l90"> *      the data is application data, the data is decoded directly into</span><a href="#l90"></a>
<span id="l91"> *      the dst buffer.</span><a href="#l91"></a>
<span id="l92"> *</span><a href="#l92"></a>
<span id="l93"> *      In the case of an outbound record, when the write to the</span><a href="#l93"></a>
<span id="l94"> *      &quot;real&quot; OutputStream's would normally take place, instead we</span><a href="#l94"></a>
<span id="l95"> *      call back up to the EngineOutputRecord's version of</span><a href="#l95"></a>
<span id="l96"> *      writeBuffer, at which time we capture the resulting output in a</span><a href="#l96"></a>
<span id="l97"> *      ByteBuffer, and send that back to the EngineWriter for internal</span><a href="#l97"></a>
<span id="l98"> *      storage.</span><a href="#l98"></a>
<span id="l99"> *</span><a href="#l99"></a>
<span id="l100"> *      EngineWriter is responsible for &quot;handling&quot; all outbound</span><a href="#l100"></a>
<span id="l101"> *      data, be it handshake or app data, and for returning the data</span><a href="#l101"></a>
<span id="l102"> *      to wrap() in the proper order.</span><a href="#l102"></a>
<span id="l103"> *</span><a href="#l103"></a>
<span id="l104"> * ClientHandshaker/ServerHandshaker/Handshaker:</span><a href="#l104"></a>
<span id="l105"> *      Methods which relied on SSLSocket now have work on either</span><a href="#l105"></a>
<span id="l106"> *      SSLSockets or SSLEngines.</span><a href="#l106"></a>
<span id="l107"> *</span><a href="#l107"></a>
<span id="l108"> * @author Brad Wetmore</span><a href="#l108"></a>
<span id="l109"> */</span><a href="#l109"></a>
<span id="l110">final public class SSLEngineImpl extends SSLEngine {</span><a href="#l110"></a>
<span id="l111"></span><a href="#l111"></a>
<span id="l112">    //</span><a href="#l112"></a>
<span id="l113">    // Fields and global comments</span><a href="#l113"></a>
<span id="l114">    //</span><a href="#l114"></a>
<span id="l115"></span><a href="#l115"></a>
<span id="l116">    /*</span><a href="#l116"></a>
<span id="l117">     * There's a state machine associated with each connection, which</span><a href="#l117"></a>
<span id="l118">     * among other roles serves to negotiate session changes.</span><a href="#l118"></a>
<span id="l119">     *</span><a href="#l119"></a>
<span id="l120">     * - START with constructor, until the TCP connection's around.</span><a href="#l120"></a>
<span id="l121">     * - HANDSHAKE picks session parameters before allowing traffic.</span><a href="#l121"></a>
<span id="l122">     *          There are many substates due to sequencing requirements</span><a href="#l122"></a>
<span id="l123">     *          for handshake messages.</span><a href="#l123"></a>
<span id="l124">     * - DATA may be transmitted.</span><a href="#l124"></a>
<span id="l125">     * - RENEGOTIATE state allows concurrent data and handshaking</span><a href="#l125"></a>
<span id="l126">     *          traffic (&quot;same&quot; substates as HANDSHAKE), and terminates</span><a href="#l126"></a>
<span id="l127">     *          in selection of new session (and connection) parameters</span><a href="#l127"></a>
<span id="l128">     * - ERROR state immediately precedes abortive disconnect.</span><a href="#l128"></a>
<span id="l129">     * - CLOSED when one side closes down, used to start the shutdown</span><a href="#l129"></a>
<span id="l130">     *          process.  SSL connection objects are not reused.</span><a href="#l130"></a>
<span id="l131">     *</span><a href="#l131"></a>
<span id="l132">     * State affects what SSL record types may legally be sent:</span><a href="#l132"></a>
<span id="l133">     *</span><a href="#l133"></a>
<span id="l134">     * - Handshake ... only in HANDSHAKE and RENEGOTIATE states</span><a href="#l134"></a>
<span id="l135">     * - App Data ... only in DATA and RENEGOTIATE states</span><a href="#l135"></a>
<span id="l136">     * - Alert ... in HANDSHAKE, DATA, RENEGOTIATE</span><a href="#l136"></a>
<span id="l137">     *</span><a href="#l137"></a>
<span id="l138">     * Re what may be received:  same as what may be sent, except that</span><a href="#l138"></a>
<span id="l139">     * HandshakeRequest handshaking messages can come from servers even</span><a href="#l139"></a>
<span id="l140">     * in the application data state, to request entry to RENEGOTIATE.</span><a href="#l140"></a>
<span id="l141">     *</span><a href="#l141"></a>
<span id="l142">     * The state machine within HANDSHAKE and RENEGOTIATE states controls</span><a href="#l142"></a>
<span id="l143">     * the pending session, not the connection state, until the change</span><a href="#l143"></a>
<span id="l144">     * cipher spec and &quot;Finished&quot; handshake messages are processed and</span><a href="#l144"></a>
<span id="l145">     * make the &quot;new&quot; session become the current one.</span><a href="#l145"></a>
<span id="l146">     *</span><a href="#l146"></a>
<span id="l147">     * NOTE: details of the SMs always need to be nailed down better.</span><a href="#l147"></a>
<span id="l148">     * The text above illustrates the core ideas.</span><a href="#l148"></a>
<span id="l149">     *</span><a href="#l149"></a>
<span id="l150">     *                +----&gt;-------+------&gt;---------&gt;-------+</span><a href="#l150"></a>
<span id="l151">     *                |            |                        |</span><a href="#l151"></a>
<span id="l152">     *     &lt;-----&lt;    ^            ^  &lt;-----&lt;               |</span><a href="#l152"></a>
<span id="l153">     *START&gt;-----&gt;HANDSHAKE&gt;-----&gt;DATA&gt;-----&gt;RENEGOTIATE    |</span><a href="#l153"></a>
<span id="l154">     *                v            v               v        |</span><a href="#l154"></a>
<span id="l155">     *                |            |               |        |</span><a href="#l155"></a>
<span id="l156">     *                +------------+---------------+        |</span><a href="#l156"></a>
<span id="l157">     *                |                                     |</span><a href="#l157"></a>
<span id="l158">     *                v                                     |</span><a href="#l158"></a>
<span id="l159">     *               ERROR&gt;------&gt;-----&gt;CLOSED&lt;--------&lt;----+</span><a href="#l159"></a>
<span id="l160">     *</span><a href="#l160"></a>
<span id="l161">     * ALSO, note that the the purpose of handshaking (renegotiation is</span><a href="#l161"></a>
<span id="l162">     * included) is to assign a different, and perhaps new, session to</span><a href="#l162"></a>
<span id="l163">     * the connection.  The SSLv3 spec is a bit confusing on that new</span><a href="#l163"></a>
<span id="l164">     * protocol feature.</span><a href="#l164"></a>
<span id="l165">     */</span><a href="#l165"></a>
<span id="l166">    private int                 connectionState;</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">    private static final int    cs_START = 0;</span><a href="#l168"></a>
<span id="l169">    private static final int    cs_HANDSHAKE = 1;</span><a href="#l169"></a>
<span id="l170">    private static final int    cs_DATA = 2;</span><a href="#l170"></a>
<span id="l171">    private static final int    cs_RENEGOTIATE = 3;</span><a href="#l171"></a>
<span id="l172">    private static final int    cs_ERROR = 4;</span><a href="#l172"></a>
<span id="l173">    private static final int    cs_CLOSED = 6;</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    /*</span><a href="#l175"></a>
<span id="l176">     * Once we're in state cs_CLOSED, we can continue to</span><a href="#l176"></a>
<span id="l177">     * wrap/unwrap until we finish sending/receiving the messages</span><a href="#l177"></a>
<span id="l178">     * for close_notify.  EngineWriter handles outboundDone.</span><a href="#l178"></a>
<span id="l179">     */</span><a href="#l179"></a>
<span id="l180">    private boolean             inboundDone = false;</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    EngineWriter                writer;</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">    /*</span><a href="#l184"></a>
<span id="l185">     * The authentication context holds all information used to establish</span><a href="#l185"></a>
<span id="l186">     * who this end of the connection is (certificate chains, private keys,</span><a href="#l186"></a>
<span id="l187">     * etc) and who is trusted (e.g. as CAs or websites).</span><a href="#l187"></a>
<span id="l188">     */</span><a href="#l188"></a>
<span id="l189">    private SSLContextImpl      sslContext;</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    /*</span><a href="#l191"></a>
<span id="l192">     * This connection is one of (potentially) many associated with</span><a href="#l192"></a>
<span id="l193">     * any given session.  The output of the handshake protocol is a</span><a href="#l193"></a>
<span id="l194">     * new session ... although all the protocol description talks</span><a href="#l194"></a>
<span id="l195">     * about changing the cipher spec (and it does change), in fact</span><a href="#l195"></a>
<span id="l196">     * that's incidental since it's done by changing everything that</span><a href="#l196"></a>
<span id="l197">     * is associated with a session at the same time.  (TLS/IETF may</span><a href="#l197"></a>
<span id="l198">     * change that to add client authentication w/o new key exchg.)</span><a href="#l198"></a>
<span id="l199">     */</span><a href="#l199"></a>
<span id="l200">    private Handshaker                  handshaker;</span><a href="#l200"></a>
<span id="l201">    private SSLSessionImpl              sess;</span><a href="#l201"></a>
<span id="l202">    private volatile SSLSessionImpl     handshakeSession;</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    /*</span><a href="#l205"></a>
<span id="l206">     * Client authentication be off, requested, or required.</span><a href="#l206"></a>
<span id="l207">     *</span><a href="#l207"></a>
<span id="l208">     * This will be used by both this class and SSLSocket's variants.</span><a href="#l208"></a>
<span id="l209">     */</span><a href="#l209"></a>
<span id="l210">    static final byte           clauth_none = 0;</span><a href="#l210"></a>
<span id="l211">    static final byte           clauth_requested = 1;</span><a href="#l211"></a>
<span id="l212">    static final byte           clauth_required = 2;</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">    /*</span><a href="#l214"></a>
<span id="l215">     * Flag indicating if the next record we receive MUST be a Finished</span><a href="#l215"></a>
<span id="l216">     * message. Temporarily set during the handshake to ensure that</span><a href="#l216"></a>
<span id="l217">     * a change cipher spec message is followed by a finished message.</span><a href="#l217"></a>
<span id="l218">     */</span><a href="#l218"></a>
<span id="l219">    private boolean             expectingFinished;</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221"></span><a href="#l221"></a>
<span id="l222">    /*</span><a href="#l222"></a>
<span id="l223">     * If someone tries to closeInbound() (say at End-Of-Stream)</span><a href="#l223"></a>
<span id="l224">     * our engine having received a close_notify, we need to</span><a href="#l224"></a>
<span id="l225">     * notify the app that we may have a truncation attack underway.</span><a href="#l225"></a>
<span id="l226">     */</span><a href="#l226"></a>
<span id="l227">    private boolean             recvCN;</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    /*</span><a href="#l229"></a>
<span id="l230">     * For improved diagnostics, we detail connection closure</span><a href="#l230"></a>
<span id="l231">     * If the engine is closed (connectionState &gt;= cs_ERROR),</span><a href="#l231"></a>
<span id="l232">     * closeReason != null indicates if the engine was closed</span><a href="#l232"></a>
<span id="l233">     * because of an error or because or normal shutdown.</span><a href="#l233"></a>
<span id="l234">     */</span><a href="#l234"></a>
<span id="l235">    private SSLException        closeReason;</span><a href="#l235"></a>
<span id="l236"></span><a href="#l236"></a>
<span id="l237">    /*</span><a href="#l237"></a>
<span id="l238">     * Per-connection private state that doesn't change when the</span><a href="#l238"></a>
<span id="l239">     * session is changed.</span><a href="#l239"></a>
<span id="l240">     */</span><a href="#l240"></a>
<span id="l241">    private byte                        doClientAuth;</span><a href="#l241"></a>
<span id="l242">    private boolean                     enableSessionCreation = true;</span><a href="#l242"></a>
<span id="l243">    EngineInputRecord                   inputRecord;</span><a href="#l243"></a>
<span id="l244">    EngineOutputRecord                  outputRecord;</span><a href="#l244"></a>
<span id="l245">    private AccessControlContext        acc;</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    // The cipher suites enabled for use on this connection.</span><a href="#l247"></a>
<span id="l248">    private CipherSuiteList             enabledCipherSuites;</span><a href="#l248"></a>
<span id="l249"></span><a href="#l249"></a>
<span id="l250">    // the endpoint identification protocol</span><a href="#l250"></a>
<span id="l251">    private String                      identificationProtocol = null;</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">    // The cryptographic algorithm constraints</span><a href="#l253"></a>
<span id="l254">    private AlgorithmConstraints        algorithmConstraints = null;</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">    // Have we been told whether we're client or server?</span><a href="#l256"></a>
<span id="l257">    private boolean                     serverModeSet = false;</span><a href="#l257"></a>
<span id="l258">    private boolean                     roleIsServer;</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">    /*</span><a href="#l260"></a>
<span id="l261">     * The protocol versions enabled for use on this connection.</span><a href="#l261"></a>
<span id="l262">     *</span><a href="#l262"></a>
<span id="l263">     * Note: we support a pseudo protocol called SSLv2Hello which when</span><a href="#l263"></a>
<span id="l264">     * set will result in an SSL v2 Hello being sent with SSL (version 3.0)</span><a href="#l264"></a>
<span id="l265">     * or TLS (version 3.1, 3.2, etc.) version info.</span><a href="#l265"></a>
<span id="l266">     */</span><a href="#l266"></a>
<span id="l267">    private ProtocolList        enabledProtocols;</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">    /*</span><a href="#l269"></a>
<span id="l270">     * The SSL version associated with this connection.</span><a href="#l270"></a>
<span id="l271">     */</span><a href="#l271"></a>
<span id="l272">    private ProtocolVersion     protocolVersion = ProtocolVersion.DEFAULT;</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">    /*</span><a href="#l274"></a>
<span id="l275">     * Crypto state that's reinitialized when the session changes.</span><a href="#l275"></a>
<span id="l276">     */</span><a href="#l276"></a>
<span id="l277">    private MAC                 readMAC, writeMAC;</span><a href="#l277"></a>
<span id="l278">    private CipherBox           readCipher, writeCipher;</span><a href="#l278"></a>
<span id="l279">    // NOTE: compression state would be saved here</span><a href="#l279"></a>
<span id="l280"></span><a href="#l280"></a>
<span id="l281">    /*</span><a href="#l281"></a>
<span id="l282">     * security parameters for secure renegotiation.</span><a href="#l282"></a>
<span id="l283">     */</span><a href="#l283"></a>
<span id="l284">    private boolean             secureRenegotiation;</span><a href="#l284"></a>
<span id="l285">    private byte[]              clientVerifyData;</span><a href="#l285"></a>
<span id="l286">    private byte[]              serverVerifyData;</span><a href="#l286"></a>
<span id="l287"></span><a href="#l287"></a>
<span id="l288">    /*</span><a href="#l288"></a>
<span id="l289">     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *</span><a href="#l289"></a>
<span id="l290">     * IMPORTANT STUFF TO UNDERSTANDING THE SYNCHRONIZATION ISSUES.</span><a href="#l290"></a>
<span id="l291">     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *</span><a href="#l291"></a>
<span id="l292">     *</span><a href="#l292"></a>
<span id="l293">     * There are several locks here.</span><a href="#l293"></a>
<span id="l294">     *</span><a href="#l294"></a>
<span id="l295">     * The primary lock is the per-instance lock used by</span><a href="#l295"></a>
<span id="l296">     * synchronized(this) and the synchronized methods.  It controls all</span><a href="#l296"></a>
<span id="l297">     * access to things such as the connection state and variables which</span><a href="#l297"></a>
<span id="l298">     * affect handshaking.  If we are inside a synchronized method, we</span><a href="#l298"></a>
<span id="l299">     * can access the state directly, otherwise, we must use the</span><a href="#l299"></a>
<span id="l300">     * synchronized equivalents.</span><a href="#l300"></a>
<span id="l301">     *</span><a href="#l301"></a>
<span id="l302">     * Note that we must never acquire the &lt;code&gt;this&lt;/code&gt; lock after</span><a href="#l302"></a>
<span id="l303">     * &lt;code&gt;writeLock&lt;/code&gt; or run the risk of deadlock.</span><a href="#l303"></a>
<span id="l304">     *</span><a href="#l304"></a>
<span id="l305">     * Grab some coffee, and be careful with any code changes.</span><a href="#l305"></a>
<span id="l306">     */</span><a href="#l306"></a>
<span id="l307">    private Object              wrapLock;</span><a href="#l307"></a>
<span id="l308">    private Object              unwrapLock;</span><a href="#l308"></a>
<span id="l309">    Object                      writeLock;</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">    /*</span><a href="#l311"></a>
<span id="l312">     * Is it the first application record to write?</span><a href="#l312"></a>
<span id="l313">     */</span><a href="#l313"></a>
<span id="l314">    private boolean isFirstAppOutputRecord = true;</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    /*</span><a href="#l316"></a>
<span id="l317">     * Class and subclass dynamic debugging support</span><a href="#l317"></a>
<span id="l318">     */</span><a href="#l318"></a>
<span id="l319">    private static final Debug debug = Debug.getInstance(&quot;ssl&quot;);</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    //</span><a href="#l321"></a>
<span id="l322">    // Initialization/Constructors</span><a href="#l322"></a>
<span id="l323">    //</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">    /**</span><a href="#l325"></a>
<span id="l326">     * Constructor for an SSLEngine from SSLContext, without</span><a href="#l326"></a>
<span id="l327">     * host/port hints.  This Engine will not be able to cache</span><a href="#l327"></a>
<span id="l328">     * sessions, but must renegotiate everything by hand.</span><a href="#l328"></a>
<span id="l329">     */</span><a href="#l329"></a>
<span id="l330">    SSLEngineImpl(SSLContextImpl ctx) {</span><a href="#l330"></a>
<span id="l331">        super();</span><a href="#l331"></a>
<span id="l332">        init(ctx);</span><a href="#l332"></a>
<span id="l333">    }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">    /**</span><a href="#l335"></a>
<span id="l336">     * Constructor for an SSLEngine from SSLContext.</span><a href="#l336"></a>
<span id="l337">     */</span><a href="#l337"></a>
<span id="l338">    SSLEngineImpl(SSLContextImpl ctx, String host, int port) {</span><a href="#l338"></a>
<span id="l339">        super(host, port);</span><a href="#l339"></a>
<span id="l340">        init(ctx);</span><a href="#l340"></a>
<span id="l341">    }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">    /**</span><a href="#l343"></a>
<span id="l344">     * Initializes the Engine</span><a href="#l344"></a>
<span id="l345">     */</span><a href="#l345"></a>
<span id="l346">    private void init(SSLContextImpl ctx) {</span><a href="#l346"></a>
<span id="l347">        if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l347"></a>
<span id="l348">            System.out.println(&quot;Using SSLEngineImpl.&quot;);</span><a href="#l348"></a>
<span id="l349">        }</span><a href="#l349"></a>
<span id="l350"></span><a href="#l350"></a>
<span id="l351">        sslContext = ctx;</span><a href="#l351"></a>
<span id="l352">        sess = new SSLSessionImpl();</span><a href="#l352"></a>
<span id="l353">        handshakeSession = null;</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">        /*</span><a href="#l355"></a>
<span id="l356">         * State is cs_START until we initialize the handshaker.</span><a href="#l356"></a>
<span id="l357">         *</span><a href="#l357"></a>
<span id="l358">         * Apps using SSLEngine are probably going to be server.</span><a href="#l358"></a>
<span id="l359">         * Somewhat arbitrary choice.</span><a href="#l359"></a>
<span id="l360">         */</span><a href="#l360"></a>
<span id="l361">        roleIsServer = true;</span><a href="#l361"></a>
<span id="l362">        connectionState = cs_START;</span><a href="#l362"></a>
<span id="l363"></span><a href="#l363"></a>
<span id="l364">        /*</span><a href="#l364"></a>
<span id="l365">         * default read and write side cipher and MAC support</span><a href="#l365"></a>
<span id="l366">         *</span><a href="#l366"></a>
<span id="l367">         * Note:  compression support would go here too</span><a href="#l367"></a>
<span id="l368">         */</span><a href="#l368"></a>
<span id="l369">        readCipher = CipherBox.NULL;</span><a href="#l369"></a>
<span id="l370">        readMAC = MAC.NULL;</span><a href="#l370"></a>
<span id="l371">        writeCipher = CipherBox.NULL;</span><a href="#l371"></a>
<span id="l372">        writeMAC = MAC.NULL;</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">        // default security parameters for secure renegotiation</span><a href="#l374"></a>
<span id="l375">        secureRenegotiation = false;</span><a href="#l375"></a>
<span id="l376">        clientVerifyData = new byte[0];</span><a href="#l376"></a>
<span id="l377">        serverVerifyData = new byte[0];</span><a href="#l377"></a>
<span id="l378"></span><a href="#l378"></a>
<span id="l379">        enabledCipherSuites =</span><a href="#l379"></a>
<span id="l380">                sslContext.getDefaultCipherSuiteList(roleIsServer);</span><a href="#l380"></a>
<span id="l381">        enabledProtocols =</span><a href="#l381"></a>
<span id="l382">                sslContext.getDefaultProtocolList(roleIsServer);</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">        wrapLock = new Object();</span><a href="#l384"></a>
<span id="l385">        unwrapLock = new Object();</span><a href="#l385"></a>
<span id="l386">        writeLock = new Object();</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">        /*</span><a href="#l388"></a>
<span id="l389">         * Save the Access Control Context.  This will be used later</span><a href="#l389"></a>
<span id="l390">         * for a couple of things, including providing a context to</span><a href="#l390"></a>
<span id="l391">         * run tasks in, and for determining which credentials</span><a href="#l391"></a>
<span id="l392">         * to use for Subject based (JAAS) decisions</span><a href="#l392"></a>
<span id="l393">         */</span><a href="#l393"></a>
<span id="l394">        acc = AccessController.getContext();</span><a href="#l394"></a>
<span id="l395"></span><a href="#l395"></a>
<span id="l396">        /*</span><a href="#l396"></a>
<span id="l397">         * All outbound application data goes through this OutputRecord,</span><a href="#l397"></a>
<span id="l398">         * other data goes through their respective records created</span><a href="#l398"></a>
<span id="l399">         * elsewhere.  All inbound data goes through this one</span><a href="#l399"></a>
<span id="l400">         * input record.</span><a href="#l400"></a>
<span id="l401">         */</span><a href="#l401"></a>
<span id="l402">        outputRecord =</span><a href="#l402"></a>
<span id="l403">            new EngineOutputRecord(Record.ct_application_data, this);</span><a href="#l403"></a>
<span id="l404">        inputRecord = new EngineInputRecord(this);</span><a href="#l404"></a>
<span id="l405">        inputRecord.enableFormatChecks();</span><a href="#l405"></a>
<span id="l406"></span><a href="#l406"></a>
<span id="l407">        writer = new EngineWriter();</span><a href="#l407"></a>
<span id="l408">    }</span><a href="#l408"></a>
<span id="l409"></span><a href="#l409"></a>
<span id="l410">    /**</span><a href="#l410"></a>
<span id="l411">     * Initialize the handshaker object. This means:</span><a href="#l411"></a>
<span id="l412">     *</span><a href="#l412"></a>
<span id="l413">     *  . if a handshake is already in progress (state is cs_HANDSHAKE</span><a href="#l413"></a>
<span id="l414">     *    or cs_RENEGOTIATE), do nothing and return</span><a href="#l414"></a>
<span id="l415">     *</span><a href="#l415"></a>
<span id="l416">     *  . if the engine is already closed, throw an Exception (internal error)</span><a href="#l416"></a>
<span id="l417">     *</span><a href="#l417"></a>
<span id="l418">     *  . otherwise (cs_START or cs_DATA), create the appropriate handshaker</span><a href="#l418"></a>
<span id="l419">     *    object and advance the connection state (to cs_HANDSHAKE or</span><a href="#l419"></a>
<span id="l420">     *    cs_RENEGOTIATE, respectively).</span><a href="#l420"></a>
<span id="l421">     *</span><a href="#l421"></a>
<span id="l422">     * This method is called right after a new engine is created, when</span><a href="#l422"></a>
<span id="l423">     * starting renegotiation, or when changing client/server mode of the</span><a href="#l423"></a>
<span id="l424">     * engine.</span><a href="#l424"></a>
<span id="l425">     */</span><a href="#l425"></a>
<span id="l426">    private void initHandshaker() {</span><a href="#l426"></a>
<span id="l427">        switch (connectionState) {</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">        //</span><a href="#l429"></a>
<span id="l430">        // Starting a new handshake.</span><a href="#l430"></a>
<span id="l431">        //</span><a href="#l431"></a>
<span id="l432">        case cs_START:</span><a href="#l432"></a>
<span id="l433">        case cs_DATA:</span><a href="#l433"></a>
<span id="l434">            break;</span><a href="#l434"></a>
<span id="l435"></span><a href="#l435"></a>
<span id="l436">        //</span><a href="#l436"></a>
<span id="l437">        // We're already in the middle of a handshake.</span><a href="#l437"></a>
<span id="l438">        //</span><a href="#l438"></a>
<span id="l439">        case cs_HANDSHAKE:</span><a href="#l439"></a>
<span id="l440">        case cs_RENEGOTIATE:</span><a href="#l440"></a>
<span id="l441">            return;</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">        //</span><a href="#l443"></a>
<span id="l444">        // Anyone allowed to call this routine is required to</span><a href="#l444"></a>
<span id="l445">        // do so ONLY if the connection state is reasonable...</span><a href="#l445"></a>
<span id="l446">        //</span><a href="#l446"></a>
<span id="l447">        default:</span><a href="#l447"></a>
<span id="l448">            throw new IllegalStateException(&quot;Internal error&quot;);</span><a href="#l448"></a>
<span id="l449">        }</span><a href="#l449"></a>
<span id="l450"></span><a href="#l450"></a>
<span id="l451">        // state is either cs_START or cs_DATA</span><a href="#l451"></a>
<span id="l452">        if (connectionState == cs_START) {</span><a href="#l452"></a>
<span id="l453">            connectionState = cs_HANDSHAKE;</span><a href="#l453"></a>
<span id="l454">        } else { // cs_DATA</span><a href="#l454"></a>
<span id="l455">            connectionState = cs_RENEGOTIATE;</span><a href="#l455"></a>
<span id="l456">        }</span><a href="#l456"></a>
<span id="l457">        if (roleIsServer) {</span><a href="#l457"></a>
<span id="l458">            handshaker = new ServerHandshaker(this, sslContext,</span><a href="#l458"></a>
<span id="l459">                    enabledProtocols, doClientAuth,</span><a href="#l459"></a>
<span id="l460">                    protocolVersion, connectionState == cs_HANDSHAKE,</span><a href="#l460"></a>
<span id="l461">                    secureRenegotiation, clientVerifyData, serverVerifyData);</span><a href="#l461"></a>
<span id="l462">        } else {</span><a href="#l462"></a>
<span id="l463">            handshaker = new ClientHandshaker(this, sslContext,</span><a href="#l463"></a>
<span id="l464">                    enabledProtocols,</span><a href="#l464"></a>
<span id="l465">                    protocolVersion, connectionState == cs_HANDSHAKE,</span><a href="#l465"></a>
<span id="l466">                    secureRenegotiation, clientVerifyData, serverVerifyData);</span><a href="#l466"></a>
<span id="l467">        }</span><a href="#l467"></a>
<span id="l468">        handshaker.setEnabledCipherSuites(enabledCipherSuites);</span><a href="#l468"></a>
<span id="l469">        handshaker.setEnableSessionCreation(enableSessionCreation);</span><a href="#l469"></a>
<span id="l470">    }</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">    /*</span><a href="#l472"></a>
<span id="l473">     * Report the current status of the Handshaker</span><a href="#l473"></a>
<span id="l474">     */</span><a href="#l474"></a>
<span id="l475">    private HandshakeStatus getHSStatus(HandshakeStatus hss) {</span><a href="#l475"></a>
<span id="l476"></span><a href="#l476"></a>
<span id="l477">        if (hss != null) {</span><a href="#l477"></a>
<span id="l478">            return hss;</span><a href="#l478"></a>
<span id="l479">        }</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481">        synchronized (this) {</span><a href="#l481"></a>
<span id="l482">            if (writer.hasOutboundData()) {</span><a href="#l482"></a>
<span id="l483">                return HandshakeStatus.NEED_WRAP;</span><a href="#l483"></a>
<span id="l484">            } else if (handshaker != null) {</span><a href="#l484"></a>
<span id="l485">                if (handshaker.taskOutstanding()) {</span><a href="#l485"></a>
<span id="l486">                    return HandshakeStatus.NEED_TASK;</span><a href="#l486"></a>
<span id="l487">                } else {</span><a href="#l487"></a>
<span id="l488">                    return HandshakeStatus.NEED_UNWRAP;</span><a href="#l488"></a>
<span id="l489">                }</span><a href="#l489"></a>
<span id="l490">            } else if (connectionState == cs_CLOSED) {</span><a href="#l490"></a>
<span id="l491">                /*</span><a href="#l491"></a>
<span id="l492">                 * Special case where we're closing, but</span><a href="#l492"></a>
<span id="l493">                 * still need the close_notify before we</span><a href="#l493"></a>
<span id="l494">                 * can officially be closed.</span><a href="#l494"></a>
<span id="l495">                 *</span><a href="#l495"></a>
<span id="l496">                 * Note isOutboundDone is taken care of by</span><a href="#l496"></a>
<span id="l497">                 * hasOutboundData() above.</span><a href="#l497"></a>
<span id="l498">                 */</span><a href="#l498"></a>
<span id="l499">                if (!isInboundDone()) {</span><a href="#l499"></a>
<span id="l500">                    return HandshakeStatus.NEED_UNWRAP;</span><a href="#l500"></a>
<span id="l501">                } // else not handshaking</span><a href="#l501"></a>
<span id="l502">            }</span><a href="#l502"></a>
<span id="l503">            return HandshakeStatus.NOT_HANDSHAKING;</span><a href="#l503"></a>
<span id="l504">        }</span><a href="#l504"></a>
<span id="l505">    }</span><a href="#l505"></a>
<span id="l506"></span><a href="#l506"></a>
<span id="l507">    synchronized private void checkTaskThrown() throws SSLException {</span><a href="#l507"></a>
<span id="l508">        if (handshaker != null) {</span><a href="#l508"></a>
<span id="l509">            handshaker.checkThrown();</span><a href="#l509"></a>
<span id="l510">        }</span><a href="#l510"></a>
<span id="l511">    }</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">    //</span><a href="#l513"></a>
<span id="l514">    // Handshaking and connection state code</span><a href="#l514"></a>
<span id="l515">    //</span><a href="#l515"></a>
<span id="l516"></span><a href="#l516"></a>
<span id="l517">    /*</span><a href="#l517"></a>
<span id="l518">     * Provides &quot;this&quot; synchronization for connection state.</span><a href="#l518"></a>
<span id="l519">     * Otherwise, you can access it directly.</span><a href="#l519"></a>
<span id="l520">     */</span><a href="#l520"></a>
<span id="l521">    synchronized private int getConnectionState() {</span><a href="#l521"></a>
<span id="l522">        return connectionState;</span><a href="#l522"></a>
<span id="l523">    }</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">    synchronized private void setConnectionState(int state) {</span><a href="#l525"></a>
<span id="l526">        connectionState = state;</span><a href="#l526"></a>
<span id="l527">    }</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">    /*</span><a href="#l529"></a>
<span id="l530">     * Get the Access Control Context.</span><a href="#l530"></a>
<span id="l531">     *</span><a href="#l531"></a>
<span id="l532">     * Used for a known context to</span><a href="#l532"></a>
<span id="l533">     * run tasks in, and for determining which credentials</span><a href="#l533"></a>
<span id="l534">     * to use for Subject-based (JAAS) decisions.</span><a href="#l534"></a>
<span id="l535">     */</span><a href="#l535"></a>
<span id="l536">    AccessControlContext getAcc() {</span><a href="#l536"></a>
<span id="l537">        return acc;</span><a href="#l537"></a>
<span id="l538">    }</span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">    /*</span><a href="#l540"></a>
<span id="l541">     * Is a handshake currently underway?</span><a href="#l541"></a>
<span id="l542">     */</span><a href="#l542"></a>
<span id="l543">    public SSLEngineResult.HandshakeStatus getHandshakeStatus() {</span><a href="#l543"></a>
<span id="l544">        return getHSStatus(null);</span><a href="#l544"></a>
<span id="l545">    }</span><a href="#l545"></a>
<span id="l546"></span><a href="#l546"></a>
<span id="l547">    /*</span><a href="#l547"></a>
<span id="l548">     * When a connection finishes handshaking by enabling use of a newly</span><a href="#l548"></a>
<span id="l549">     * negotiated session, each end learns about it in two halves (read,</span><a href="#l549"></a>
<span id="l550">     * and write).  When both read and write ciphers have changed, and the</span><a href="#l550"></a>
<span id="l551">     * last handshake message has been read, the connection has joined</span><a href="#l551"></a>
<span id="l552">     * (rejoined) the new session.</span><a href="#l552"></a>
<span id="l553">     *</span><a href="#l553"></a>
<span id="l554">     * NOTE:  The SSLv3 spec is rather unclear on the concepts here.</span><a href="#l554"></a>
<span id="l555">     * Sessions don't change once they're established (including cipher</span><a href="#l555"></a>
<span id="l556">     * suite and master secret) but connections can join them (and leave</span><a href="#l556"></a>
<span id="l557">     * them).  They're created by handshaking, though sometime handshaking</span><a href="#l557"></a>
<span id="l558">     * causes connections to join up with pre-established sessions.</span><a href="#l558"></a>
<span id="l559">     *</span><a href="#l559"></a>
<span id="l560">     * Synchronized on &quot;this&quot; from readRecord.</span><a href="#l560"></a>
<span id="l561">     */</span><a href="#l561"></a>
<span id="l562">    private void changeReadCiphers() throws SSLException {</span><a href="#l562"></a>
<span id="l563">        CipherBox oldCipher = readCipher;</span><a href="#l563"></a>
<span id="l564"></span><a href="#l564"></a>
<span id="l565">        try {</span><a href="#l565"></a>
<span id="l566">            readCipher = handshaker.newReadCipher();</span><a href="#l566"></a>
<span id="l567">            readMAC = handshaker.newReadMAC();</span><a href="#l567"></a>
<span id="l568">        } catch (GeneralSecurityException e) {</span><a href="#l568"></a>
<span id="l569">            // &quot;can't happen&quot;</span><a href="#l569"></a>
<span id="l570">            throw new SSLException(&quot;Algorithm missing:  &quot;, e);</span><a href="#l570"></a>
<span id="l571">        }</span><a href="#l571"></a>
<span id="l572"></span><a href="#l572"></a>
<span id="l573">        /*</span><a href="#l573"></a>
<span id="l574">         * Dispose of any intermediate state in the underlying cipher.</span><a href="#l574"></a>
<span id="l575">         * For PKCS11 ciphers, this will release any attached sessions,</span><a href="#l575"></a>
<span id="l576">         * and thus make finalization faster.</span><a href="#l576"></a>
<span id="l577">         *</span><a href="#l577"></a>
<span id="l578">         * Since MAC's doFinal() is called for every SSL/TLS packet, it's</span><a href="#l578"></a>
<span id="l579">         * not necessary to do the same with MAC's.</span><a href="#l579"></a>
<span id="l580">         */</span><a href="#l580"></a>
<span id="l581">        oldCipher.dispose();</span><a href="#l581"></a>
<span id="l582">    }</span><a href="#l582"></a>
<span id="l583"></span><a href="#l583"></a>
<span id="l584">    /*</span><a href="#l584"></a>
<span id="l585">     * used by Handshaker to change the active write cipher, follows</span><a href="#l585"></a>
<span id="l586">     * the output of the CCS message.</span><a href="#l586"></a>
<span id="l587">     *</span><a href="#l587"></a>
<span id="l588">     * Also synchronized on &quot;this&quot; from readRecord/delegatedTask.</span><a href="#l588"></a>
<span id="l589">     */</span><a href="#l589"></a>
<span id="l590">    void changeWriteCiphers() throws SSLException {</span><a href="#l590"></a>
<span id="l591">        if (connectionState != cs_HANDSHAKE</span><a href="#l591"></a>
<span id="l592">                &amp;&amp; connectionState != cs_RENEGOTIATE) {</span><a href="#l592"></a>
<span id="l593">            throw new SSLProtocolException(</span><a href="#l593"></a>
<span id="l594">                &quot;State error, change cipher specs&quot;);</span><a href="#l594"></a>
<span id="l595">        }</span><a href="#l595"></a>
<span id="l596"></span><a href="#l596"></a>
<span id="l597">        // ... create compressor</span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">        CipherBox oldCipher = writeCipher;</span><a href="#l599"></a>
<span id="l600"></span><a href="#l600"></a>
<span id="l601">        try {</span><a href="#l601"></a>
<span id="l602">            writeCipher = handshaker.newWriteCipher();</span><a href="#l602"></a>
<span id="l603">            writeMAC = handshaker.newWriteMAC();</span><a href="#l603"></a>
<span id="l604">        } catch (GeneralSecurityException e) {</span><a href="#l604"></a>
<span id="l605">            // &quot;can't happen&quot;</span><a href="#l605"></a>
<span id="l606">            throw new SSLException(&quot;Algorithm missing:  &quot;, e);</span><a href="#l606"></a>
<span id="l607">        }</span><a href="#l607"></a>
<span id="l608"></span><a href="#l608"></a>
<span id="l609">        // See comment above.</span><a href="#l609"></a>
<span id="l610">        oldCipher.dispose();</span><a href="#l610"></a>
<span id="l611"></span><a href="#l611"></a>
<span id="l612">        // reset the flag of the first application record</span><a href="#l612"></a>
<span id="l613">        isFirstAppOutputRecord = true;</span><a href="#l613"></a>
<span id="l614">    }</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    /*</span><a href="#l616"></a>
<span id="l617">     * Updates the SSL version associated with this connection.</span><a href="#l617"></a>
<span id="l618">     * Called from Handshaker once it has determined the negotiated version.</span><a href="#l618"></a>
<span id="l619">     */</span><a href="#l619"></a>
<span id="l620">    synchronized void setVersion(ProtocolVersion protocolVersion) {</span><a href="#l620"></a>
<span id="l621">        this.protocolVersion = protocolVersion;</span><a href="#l621"></a>
<span id="l622">        outputRecord.setVersion(protocolVersion);</span><a href="#l622"></a>
<span id="l623">    }</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625"></span><a href="#l625"></a>
<span id="l626">    /**</span><a href="#l626"></a>
<span id="l627">     * Kickstart the handshake if it is not already in progress.</span><a href="#l627"></a>
<span id="l628">     * This means:</span><a href="#l628"></a>
<span id="l629">     *</span><a href="#l629"></a>
<span id="l630">     *  . if handshaking is already underway, do nothing and return</span><a href="#l630"></a>
<span id="l631">     *</span><a href="#l631"></a>
<span id="l632">     *  . if the engine is not connected or already closed, throw an</span><a href="#l632"></a>
<span id="l633">     *    Exception.</span><a href="#l633"></a>
<span id="l634">     *</span><a href="#l634"></a>
<span id="l635">     *  . otherwise, call initHandshake() to initialize the handshaker</span><a href="#l635"></a>
<span id="l636">     *    object and progress the state. Then, send the initial</span><a href="#l636"></a>
<span id="l637">     *    handshaking message if appropriate (always on clients and</span><a href="#l637"></a>
<span id="l638">     *    on servers when renegotiating).</span><a href="#l638"></a>
<span id="l639">     */</span><a href="#l639"></a>
<span id="l640">    private synchronized void kickstartHandshake() throws IOException {</span><a href="#l640"></a>
<span id="l641">        switch (connectionState) {</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">        case cs_START:</span><a href="#l643"></a>
<span id="l644">            if (!serverModeSet) {</span><a href="#l644"></a>
<span id="l645">                throw new IllegalStateException(</span><a href="#l645"></a>
<span id="l646">                    &quot;Client/Server mode not yet set.&quot;);</span><a href="#l646"></a>
<span id="l647">            }</span><a href="#l647"></a>
<span id="l648">            initHandshaker();</span><a href="#l648"></a>
<span id="l649">            break;</span><a href="#l649"></a>
<span id="l650"></span><a href="#l650"></a>
<span id="l651">        case cs_HANDSHAKE:</span><a href="#l651"></a>
<span id="l652">            // handshaker already setup, proceed</span><a href="#l652"></a>
<span id="l653">            break;</span><a href="#l653"></a>
<span id="l654"></span><a href="#l654"></a>
<span id="l655">        case cs_DATA:</span><a href="#l655"></a>
<span id="l656">            if (!secureRenegotiation &amp;&amp; !Handshaker.allowUnsafeRenegotiation) {</span><a href="#l656"></a>
<span id="l657">                throw new SSLHandshakeException(</span><a href="#l657"></a>
<span id="l658">                        &quot;Insecure renegotiation is not allowed&quot;);</span><a href="#l658"></a>
<span id="l659">            }</span><a href="#l659"></a>
<span id="l660"></span><a href="#l660"></a>
<span id="l661">            if (!secureRenegotiation) {</span><a href="#l661"></a>
<span id="l662">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l662"></a>
<span id="l663">                    System.out.println(</span><a href="#l663"></a>
<span id="l664">                        &quot;Warning: Using insecure renegotiation&quot;);</span><a href="#l664"></a>
<span id="l665">                }</span><a href="#l665"></a>
<span id="l666">            }</span><a href="#l666"></a>
<span id="l667"></span><a href="#l667"></a>
<span id="l668">            // initialize the handshaker, move to cs_RENEGOTIATE</span><a href="#l668"></a>
<span id="l669">            initHandshaker();</span><a href="#l669"></a>
<span id="l670">            break;</span><a href="#l670"></a>
<span id="l671"></span><a href="#l671"></a>
<span id="l672">        case cs_RENEGOTIATE:</span><a href="#l672"></a>
<span id="l673">            // handshaking already in progress, return</span><a href="#l673"></a>
<span id="l674">            return;</span><a href="#l674"></a>
<span id="l675"></span><a href="#l675"></a>
<span id="l676">        default:</span><a href="#l676"></a>
<span id="l677">            // cs_ERROR/cs_CLOSED</span><a href="#l677"></a>
<span id="l678">            throw new SSLException(&quot;SSLEngine is closing/closed&quot;);</span><a href="#l678"></a>
<span id="l679">        }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">        //</span><a href="#l681"></a>
<span id="l682">        // Kickstart handshake state machine if we need to ...</span><a href="#l682"></a>
<span id="l683">        //</span><a href="#l683"></a>
<span id="l684">        // Note that handshaker.kickstart() writes the message</span><a href="#l684"></a>
<span id="l685">        // to its HandshakeOutStream, which calls back into</span><a href="#l685"></a>
<span id="l686">        // SSLSocketImpl.writeRecord() to send it.</span><a href="#l686"></a>
<span id="l687">        //</span><a href="#l687"></a>
<span id="l688">        if (!handshaker.activated()) {</span><a href="#l688"></a>
<span id="l689">             // prior to handshaking, activate the handshake</span><a href="#l689"></a>
<span id="l690">            if (connectionState == cs_RENEGOTIATE) {</span><a href="#l690"></a>
<span id="l691">                // don't use SSLv2Hello when renegotiating</span><a href="#l691"></a>
<span id="l692">                handshaker.activate(protocolVersion);</span><a href="#l692"></a>
<span id="l693">            } else {</span><a href="#l693"></a>
<span id="l694">                handshaker.activate(null);</span><a href="#l694"></a>
<span id="l695">            }</span><a href="#l695"></a>
<span id="l696"></span><a href="#l696"></a>
<span id="l697">            if (handshaker instanceof ClientHandshaker) {</span><a href="#l697"></a>
<span id="l698">                // send client hello</span><a href="#l698"></a>
<span id="l699">                handshaker.kickstart();</span><a href="#l699"></a>
<span id="l700">            } else {    // instanceof ServerHandshaker</span><a href="#l700"></a>
<span id="l701">                if (connectionState == cs_HANDSHAKE) {</span><a href="#l701"></a>
<span id="l702">                    // initial handshake, no kickstart message to send</span><a href="#l702"></a>
<span id="l703">                } else {</span><a href="#l703"></a>
<span id="l704">                    // we want to renegotiate, send hello request</span><a href="#l704"></a>
<span id="l705">                    handshaker.kickstart();</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">                    // hello request is not included in the handshake</span><a href="#l707"></a>
<span id="l708">                    // hashes, reset them</span><a href="#l708"></a>
<span id="l709">                    handshaker.handshakeHash.reset();</span><a href="#l709"></a>
<span id="l710">                }</span><a href="#l710"></a>
<span id="l711">            }</span><a href="#l711"></a>
<span id="l712">        }</span><a href="#l712"></a>
<span id="l713">    }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">    /*</span><a href="#l715"></a>
<span id="l716">     * Start a SSLEngine handshake</span><a href="#l716"></a>
<span id="l717">     */</span><a href="#l717"></a>
<span id="l718">    public void beginHandshake() throws SSLException {</span><a href="#l718"></a>
<span id="l719">        try {</span><a href="#l719"></a>
<span id="l720">            kickstartHandshake();</span><a href="#l720"></a>
<span id="l721">        } catch (Exception e) {</span><a href="#l721"></a>
<span id="l722">            fatal(Alerts.alert_handshake_failure,</span><a href="#l722"></a>
<span id="l723">                &quot;Couldn't kickstart handshaking&quot;, e);</span><a href="#l723"></a>
<span id="l724">        }</span><a href="#l724"></a>
<span id="l725">    }</span><a href="#l725"></a>
<span id="l726"></span><a href="#l726"></a>
<span id="l727"></span><a href="#l727"></a>
<span id="l728">    //</span><a href="#l728"></a>
<span id="l729">    // Read/unwrap side</span><a href="#l729"></a>
<span id="l730">    //</span><a href="#l730"></a>
<span id="l731"></span><a href="#l731"></a>
<span id="l732"></span><a href="#l732"></a>
<span id="l733">    /**</span><a href="#l733"></a>
<span id="l734">     * Unwraps a buffer.  Does a variety of checks before grabbing</span><a href="#l734"></a>
<span id="l735">     * the unwrapLock, which blocks multiple unwraps from occurring.</span><a href="#l735"></a>
<span id="l736">     */</span><a href="#l736"></a>
<span id="l737">    public SSLEngineResult unwrap(ByteBuffer netData, ByteBuffer [] appData,</span><a href="#l737"></a>
<span id="l738">            int offset, int length) throws SSLException {</span><a href="#l738"></a>
<span id="l739"></span><a href="#l739"></a>
<span id="l740">        EngineArgs ea = new EngineArgs(netData, appData, offset, length);</span><a href="#l740"></a>
<span id="l741"></span><a href="#l741"></a>
<span id="l742">        try {</span><a href="#l742"></a>
<span id="l743">            synchronized (unwrapLock) {</span><a href="#l743"></a>
<span id="l744">                return readNetRecord(ea);</span><a href="#l744"></a>
<span id="l745">            }</span><a href="#l745"></a>
<span id="l746">        } catch (SSLProtocolException spe) {</span><a href="#l746"></a>
<span id="l747">            // may be an unexpected handshake message</span><a href="#l747"></a>
<span id="l748">            fatal(Alerts.alert_unexpected_message, spe.getMessage(), spe);</span><a href="#l748"></a>
<span id="l749">            return null;  // make compiler happy</span><a href="#l749"></a>
<span id="l750">        } catch (Exception e) {</span><a href="#l750"></a>
<span id="l751">            /*</span><a href="#l751"></a>
<span id="l752">             * Don't reset position so it looks like we didn't</span><a href="#l752"></a>
<span id="l753">             * consume anything.  We did consume something, and it</span><a href="#l753"></a>
<span id="l754">             * got us into this situation, so report that much back.</span><a href="#l754"></a>
<span id="l755">             * Our days of consuming are now over anyway.</span><a href="#l755"></a>
<span id="l756">             */</span><a href="#l756"></a>
<span id="l757">            fatal(Alerts.alert_internal_error,</span><a href="#l757"></a>
<span id="l758">                &quot;problem unwrapping net record&quot;, e);</span><a href="#l758"></a>
<span id="l759">            return null;  // make compiler happy</span><a href="#l759"></a>
<span id="l760">        } finally {</span><a href="#l760"></a>
<span id="l761">            /*</span><a href="#l761"></a>
<span id="l762">             * Just in case something failed to reset limits properly.</span><a href="#l762"></a>
<span id="l763">             */</span><a href="#l763"></a>
<span id="l764">            ea.resetLim();</span><a href="#l764"></a>
<span id="l765">        }</span><a href="#l765"></a>
<span id="l766">    }</span><a href="#l766"></a>
<span id="l767"></span><a href="#l767"></a>
<span id="l768">    /*</span><a href="#l768"></a>
<span id="l769">     * Makes additional checks for unwrap, but this time more</span><a href="#l769"></a>
<span id="l770">     * specific to this packet and the current state of the machine.</span><a href="#l770"></a>
<span id="l771">     */</span><a href="#l771"></a>
<span id="l772">    private SSLEngineResult readNetRecord(EngineArgs ea) throws IOException {</span><a href="#l772"></a>
<span id="l773"></span><a href="#l773"></a>
<span id="l774">        Status status = null;</span><a href="#l774"></a>
<span id="l775">        HandshakeStatus hsStatus = null;</span><a href="#l775"></a>
<span id="l776"></span><a href="#l776"></a>
<span id="l777">        /*</span><a href="#l777"></a>
<span id="l778">         * See if the handshaker needs to report back some SSLException.</span><a href="#l778"></a>
<span id="l779">         */</span><a href="#l779"></a>
<span id="l780">        checkTaskThrown();</span><a href="#l780"></a>
<span id="l781"></span><a href="#l781"></a>
<span id="l782">        /*</span><a href="#l782"></a>
<span id="l783">         * Check if we are closing/closed.</span><a href="#l783"></a>
<span id="l784">         */</span><a href="#l784"></a>
<span id="l785">        if (isInboundDone()) {</span><a href="#l785"></a>
<span id="l786">            return new SSLEngineResult(Status.CLOSED, getHSStatus(null), 0, 0);</span><a href="#l786"></a>
<span id="l787">        }</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">        /*</span><a href="#l789"></a>
<span id="l790">         * If we're still in cs_HANDSHAKE, make sure it's been</span><a href="#l790"></a>
<span id="l791">         * started.</span><a href="#l791"></a>
<span id="l792">         */</span><a href="#l792"></a>
<span id="l793">        synchronized (this) {</span><a href="#l793"></a>
<span id="l794">            if ((connectionState == cs_HANDSHAKE) ||</span><a href="#l794"></a>
<span id="l795">                    (connectionState == cs_START)) {</span><a href="#l795"></a>
<span id="l796">                kickstartHandshake();</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">                /*</span><a href="#l798"></a>
<span id="l799">                 * If there's still outbound data to flush, we</span><a href="#l799"></a>
<span id="l800">                 * can return without trying to unwrap anything.</span><a href="#l800"></a>
<span id="l801">                 */</span><a href="#l801"></a>
<span id="l802">                hsStatus = getHSStatus(null);</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">                if (hsStatus == HandshakeStatus.NEED_WRAP) {</span><a href="#l804"></a>
<span id="l805">                    return new SSLEngineResult(Status.OK, hsStatus, 0, 0);</span><a href="#l805"></a>
<span id="l806">                }</span><a href="#l806"></a>
<span id="l807">            }</span><a href="#l807"></a>
<span id="l808">        }</span><a href="#l808"></a>
<span id="l809"></span><a href="#l809"></a>
<span id="l810">        /*</span><a href="#l810"></a>
<span id="l811">         * Grab a copy of this if it doesn't already exist,</span><a href="#l811"></a>
<span id="l812">         * and we can use it several places before anything major</span><a href="#l812"></a>
<span id="l813">         * happens on this side.  Races aren't critical</span><a href="#l813"></a>
<span id="l814">         * here.</span><a href="#l814"></a>
<span id="l815">         */</span><a href="#l815"></a>
<span id="l816">        if (hsStatus == null) {</span><a href="#l816"></a>
<span id="l817">            hsStatus = getHSStatus(null);</span><a href="#l817"></a>
<span id="l818">        }</span><a href="#l818"></a>
<span id="l819"></span><a href="#l819"></a>
<span id="l820">        /*</span><a href="#l820"></a>
<span id="l821">         * If we have a task outstanding, this *MUST* be done before</span><a href="#l821"></a>
<span id="l822">         * doing any more unwrapping, because we could be in the middle</span><a href="#l822"></a>
<span id="l823">         * of receiving a handshake message, for example, a finished</span><a href="#l823"></a>
<span id="l824">         * message which would change the ciphers.</span><a href="#l824"></a>
<span id="l825">         */</span><a href="#l825"></a>
<span id="l826">        if (hsStatus == HandshakeStatus.NEED_TASK) {</span><a href="#l826"></a>
<span id="l827">            return new SSLEngineResult(</span><a href="#l827"></a>
<span id="l828">                Status.OK, hsStatus, 0, 0);</span><a href="#l828"></a>
<span id="l829">        }</span><a href="#l829"></a>
<span id="l830"></span><a href="#l830"></a>
<span id="l831">        /*</span><a href="#l831"></a>
<span id="l832">         * Check the packet to make sure enough is here.</span><a href="#l832"></a>
<span id="l833">         * This will also indirectly check for 0 len packets.</span><a href="#l833"></a>
<span id="l834">         */</span><a href="#l834"></a>
<span id="l835">        int packetLen = inputRecord.bytesInCompletePacket(ea.netData);</span><a href="#l835"></a>
<span id="l836"></span><a href="#l836"></a>
<span id="l837">        // Is this packet bigger than SSL/TLS normally allows?</span><a href="#l837"></a>
<span id="l838">        if (packetLen &gt; sess.getPacketBufferSize()) {</span><a href="#l838"></a>
<span id="l839">            if (packetLen &gt; Record.maxLargeRecordSize) {</span><a href="#l839"></a>
<span id="l840">                throw new SSLProtocolException(</span><a href="#l840"></a>
<span id="l841">                    &quot;Input SSL/TLS record too big: max = &quot; +</span><a href="#l841"></a>
<span id="l842">                    Record.maxLargeRecordSize +</span><a href="#l842"></a>
<span id="l843">                    &quot; len = &quot; + packetLen);</span><a href="#l843"></a>
<span id="l844">            } else {</span><a href="#l844"></a>
<span id="l845">                // Expand the expected maximum packet/application buffer</span><a href="#l845"></a>
<span id="l846">                // sizes.</span><a href="#l846"></a>
<span id="l847">                sess.expandBufferSizes();</span><a href="#l847"></a>
<span id="l848">            }</span><a href="#l848"></a>
<span id="l849">        }</span><a href="#l849"></a>
<span id="l850"></span><a href="#l850"></a>
<span id="l851">        /*</span><a href="#l851"></a>
<span id="l852">         * Check for OVERFLOW.</span><a href="#l852"></a>
<span id="l853">         *</span><a href="#l853"></a>
<span id="l854">         * To be considered: We could delay enforcing the application buffer</span><a href="#l854"></a>
<span id="l855">         * free space requirement until after the initial handshaking.</span><a href="#l855"></a>
<span id="l856">         */</span><a href="#l856"></a>
<span id="l857">        if ((packetLen - Record.headerSize) &gt; ea.getAppRemaining()) {</span><a href="#l857"></a>
<span id="l858">            return new SSLEngineResult(Status.BUFFER_OVERFLOW, hsStatus, 0, 0);</span><a href="#l858"></a>
<span id="l859">        }</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">        // check for UNDERFLOW.</span><a href="#l861"></a>
<span id="l862">        if ((packetLen == -1) || (ea.netData.remaining() &lt; packetLen)) {</span><a href="#l862"></a>
<span id="l863">            return new SSLEngineResult(</span><a href="#l863"></a>
<span id="l864">                Status.BUFFER_UNDERFLOW, hsStatus, 0, 0);</span><a href="#l864"></a>
<span id="l865">        }</span><a href="#l865"></a>
<span id="l866"></span><a href="#l866"></a>
<span id="l867">        /*</span><a href="#l867"></a>
<span id="l868">         * We're now ready to actually do the read.</span><a href="#l868"></a>
<span id="l869">         * The only result code we really need to be exactly</span><a href="#l869"></a>
<span id="l870">         * right is the HS finished, for signaling to</span><a href="#l870"></a>
<span id="l871">         * HandshakeCompletedListeners.</span><a href="#l871"></a>
<span id="l872">         */</span><a href="#l872"></a>
<span id="l873">        try {</span><a href="#l873"></a>
<span id="l874">            hsStatus = readRecord(ea);</span><a href="#l874"></a>
<span id="l875">        } catch (SSLException e) {</span><a href="#l875"></a>
<span id="l876">            throw e;</span><a href="#l876"></a>
<span id="l877">        } catch (IOException e) {</span><a href="#l877"></a>
<span id="l878">            throw new SSLException(&quot;readRecord&quot;, e);</span><a href="#l878"></a>
<span id="l879">        }</span><a href="#l879"></a>
<span id="l880"></span><a href="#l880"></a>
<span id="l881">        /*</span><a href="#l881"></a>
<span id="l882">         * Check the various condition that we could be reporting.</span><a href="#l882"></a>
<span id="l883">         *</span><a href="#l883"></a>
<span id="l884">         * It's *possible* something might have happened between the</span><a href="#l884"></a>
<span id="l885">         * above and now, but it was better to minimally lock &quot;this&quot;</span><a href="#l885"></a>
<span id="l886">         * during the read process.  We'll return the current</span><a href="#l886"></a>
<span id="l887">         * status, which is more representative of the current state.</span><a href="#l887"></a>
<span id="l888">         *</span><a href="#l888"></a>
<span id="l889">         * status above should cover:  FINISHED, NEED_TASK</span><a href="#l889"></a>
<span id="l890">         */</span><a href="#l890"></a>
<span id="l891">        status = (isInboundDone() ? Status.CLOSED : Status.OK);</span><a href="#l891"></a>
<span id="l892">        hsStatus = getHSStatus(hsStatus);</span><a href="#l892"></a>
<span id="l893"></span><a href="#l893"></a>
<span id="l894">        return new SSLEngineResult(status, hsStatus,</span><a href="#l894"></a>
<span id="l895">            ea.deltaNet(), ea.deltaApp());</span><a href="#l895"></a>
<span id="l896">    }</span><a href="#l896"></a>
<span id="l897"></span><a href="#l897"></a>
<span id="l898">    /*</span><a href="#l898"></a>
<span id="l899">     * Actually do the read record processing.</span><a href="#l899"></a>
<span id="l900">     *</span><a href="#l900"></a>
<span id="l901">     * Returns a Status if it can make specific determinations</span><a href="#l901"></a>
<span id="l902">     * of the engine state.  In particular, we need to signal</span><a href="#l902"></a>
<span id="l903">     * that a handshake just completed.</span><a href="#l903"></a>
<span id="l904">     *</span><a href="#l904"></a>
<span id="l905">     * It would be nice to be symmetrical with the write side and move</span><a href="#l905"></a>
<span id="l906">     * the majority of this to EngineInputRecord, but there's too much</span><a href="#l906"></a>
<span id="l907">     * SSLEngine state to do that cleanly.  It must still live here.</span><a href="#l907"></a>
<span id="l908">     */</span><a href="#l908"></a>
<span id="l909">    private HandshakeStatus readRecord(EngineArgs ea) throws IOException {</span><a href="#l909"></a>
<span id="l910"></span><a href="#l910"></a>
<span id="l911">        HandshakeStatus hsStatus = null;</span><a href="#l911"></a>
<span id="l912"></span><a href="#l912"></a>
<span id="l913">        /*</span><a href="#l913"></a>
<span id="l914">         * The various operations will return new sliced BB's,</span><a href="#l914"></a>
<span id="l915">         * this will avoid having to worry about positions and</span><a href="#l915"></a>
<span id="l916">         * limits in the netBB.</span><a href="#l916"></a>
<span id="l917">         */</span><a href="#l917"></a>
<span id="l918">        ByteBuffer readBB = null;</span><a href="#l918"></a>
<span id="l919">        ByteBuffer decryptedBB = null;</span><a href="#l919"></a>
<span id="l920"></span><a href="#l920"></a>
<span id="l921">        if (getConnectionState() != cs_ERROR) {</span><a href="#l921"></a>
<span id="l922"></span><a href="#l922"></a>
<span id="l923">            /*</span><a href="#l923"></a>
<span id="l924">             * Read a record ... maybe emitting an alert if we get a</span><a href="#l924"></a>
<span id="l925">             * comprehensible but unsupported &quot;hello&quot; message during</span><a href="#l925"></a>
<span id="l926">             * format checking (e.g. V2).</span><a href="#l926"></a>
<span id="l927">             */</span><a href="#l927"></a>
<span id="l928">            try {</span><a href="#l928"></a>
<span id="l929">                readBB = inputRecord.read(ea.netData);</span><a href="#l929"></a>
<span id="l930">            } catch (IOException e) {</span><a href="#l930"></a>
<span id="l931">                fatal(Alerts.alert_unexpected_message, e);</span><a href="#l931"></a>
<span id="l932">            }</span><a href="#l932"></a>
<span id="l933"></span><a href="#l933"></a>
<span id="l934">            /*</span><a href="#l934"></a>
<span id="l935">             * The basic SSLv3 record protection involves (optional)</span><a href="#l935"></a>
<span id="l936">             * encryption for privacy, and an integrity check ensuring</span><a href="#l936"></a>
<span id="l937">             * data origin authentication.  We do them both here, and</span><a href="#l937"></a>
<span id="l938">             * throw a fatal alert if the integrity check fails.</span><a href="#l938"></a>
<span id="l939">             */</span><a href="#l939"></a>
<span id="l940">            try {</span><a href="#l940"></a>
<span id="l941">                decryptedBB = inputRecord.decrypt(readMAC, readCipher, readBB);</span><a href="#l941"></a>
<span id="l942">            } catch (BadPaddingException e) {</span><a href="#l942"></a>
<span id="l943">                byte alertType = (inputRecord.contentType() ==</span><a href="#l943"></a>
<span id="l944">                    Record.ct_handshake) ?</span><a href="#l944"></a>
<span id="l945">                        Alerts.alert_handshake_failure :</span><a href="#l945"></a>
<span id="l946">                        Alerts.alert_bad_record_mac;</span><a href="#l946"></a>
<span id="l947">                fatal(alertType, e.getMessage(), e);</span><a href="#l947"></a>
<span id="l948">            }</span><a href="#l948"></a>
<span id="l949"></span><a href="#l949"></a>
<span id="l950"></span><a href="#l950"></a>
<span id="l951">            // if (!inputRecord.decompress(c))</span><a href="#l951"></a>
<span id="l952">            //     fatal(Alerts.alert_decompression_failure,</span><a href="#l952"></a>
<span id="l953">            //     &quot;decompression failure&quot;);</span><a href="#l953"></a>
<span id="l954"></span><a href="#l954"></a>
<span id="l955"></span><a href="#l955"></a>
<span id="l956">            /*</span><a href="#l956"></a>
<span id="l957">             * Process the record.</span><a href="#l957"></a>
<span id="l958">             */</span><a href="#l958"></a>
<span id="l959"></span><a href="#l959"></a>
<span id="l960">            synchronized (this) {</span><a href="#l960"></a>
<span id="l961">                switch (inputRecord.contentType()) {</span><a href="#l961"></a>
<span id="l962">                case Record.ct_handshake:</span><a href="#l962"></a>
<span id="l963">                    /*</span><a href="#l963"></a>
<span id="l964">                     * Handshake messages always go to a pending session</span><a href="#l964"></a>
<span id="l965">                     * handshaker ... if there isn't one, create one.  This</span><a href="#l965"></a>
<span id="l966">                     * must work asynchronously, for renegotiation.</span><a href="#l966"></a>
<span id="l967">                     *</span><a href="#l967"></a>
<span id="l968">                     * NOTE that handshaking will either resume a session</span><a href="#l968"></a>
<span id="l969">                     * which was in the cache (and which might have other</span><a href="#l969"></a>
<span id="l970">                     * connections in it already), or else will start a new</span><a href="#l970"></a>
<span id="l971">                     * session (new keys exchanged) with just this connection</span><a href="#l971"></a>
<span id="l972">                     * in it.</span><a href="#l972"></a>
<span id="l973">                     */</span><a href="#l973"></a>
<span id="l974"></span><a href="#l974"></a>
<span id="l975">                    initHandshaker();</span><a href="#l975"></a>
<span id="l976">                    if (!handshaker.activated()) {</span><a href="#l976"></a>
<span id="l977">                        // prior to handshaking, activate the handshake</span><a href="#l977"></a>
<span id="l978">                        if (connectionState == cs_RENEGOTIATE) {</span><a href="#l978"></a>
<span id="l979">                            // don't use SSLv2Hello when renegotiating</span><a href="#l979"></a>
<span id="l980">                            handshaker.activate(protocolVersion);</span><a href="#l980"></a>
<span id="l981">                        } else {</span><a href="#l981"></a>
<span id="l982">                            handshaker.activate(null);</span><a href="#l982"></a>
<span id="l983">                        }</span><a href="#l983"></a>
<span id="l984">                    }</span><a href="#l984"></a>
<span id="l985"></span><a href="#l985"></a>
<span id="l986">                    /*</span><a href="#l986"></a>
<span id="l987">                     * process the handshake record ... may contain just</span><a href="#l987"></a>
<span id="l988">                     * a partial handshake message or multiple messages.</span><a href="#l988"></a>
<span id="l989">                     *</span><a href="#l989"></a>
<span id="l990">                     * The handshaker state machine will ensure that it's</span><a href="#l990"></a>
<span id="l991">                     * a finished message.</span><a href="#l991"></a>
<span id="l992">                     */</span><a href="#l992"></a>
<span id="l993">                    handshaker.process_record(inputRecord, expectingFinished);</span><a href="#l993"></a>
<span id="l994">                    expectingFinished = false;</span><a href="#l994"></a>
<span id="l995"></span><a href="#l995"></a>
<span id="l996">                    if (handshaker.invalidated) {</span><a href="#l996"></a>
<span id="l997">                        handshaker = null;</span><a href="#l997"></a>
<span id="l998">                        // if state is cs_RENEGOTIATE, revert it to cs_DATA</span><a href="#l998"></a>
<span id="l999">                        if (connectionState == cs_RENEGOTIATE) {</span><a href="#l999"></a>
<span id="l1000">                            connectionState = cs_DATA;</span><a href="#l1000"></a>
<span id="l1001">                        }</span><a href="#l1001"></a>
<span id="l1002">                    } else if (handshaker.isDone()) {</span><a href="#l1002"></a>
<span id="l1003">                        // reset the parameters for secure renegotiation.</span><a href="#l1003"></a>
<span id="l1004">                        secureRenegotiation =</span><a href="#l1004"></a>
<span id="l1005">                                        handshaker.isSecureRenegotiation();</span><a href="#l1005"></a>
<span id="l1006">                        clientVerifyData = handshaker.getClientVerifyData();</span><a href="#l1006"></a>
<span id="l1007">                        serverVerifyData = handshaker.getServerVerifyData();</span><a href="#l1007"></a>
<span id="l1008"></span><a href="#l1008"></a>
<span id="l1009">                        sess = handshaker.getSession();</span><a href="#l1009"></a>
<span id="l1010">                        handshakeSession = null;</span><a href="#l1010"></a>
<span id="l1011">                        if (!writer.hasOutboundData()) {</span><a href="#l1011"></a>
<span id="l1012">                            hsStatus = HandshakeStatus.FINISHED;</span><a href="#l1012"></a>
<span id="l1013">                        }</span><a href="#l1013"></a>
<span id="l1014">                        handshaker = null;</span><a href="#l1014"></a>
<span id="l1015">                        connectionState = cs_DATA;</span><a href="#l1015"></a>
<span id="l1016">                        // No handshakeListeners here.  That's a</span><a href="#l1016"></a>
<span id="l1017">                        // SSLSocket thing.</span><a href="#l1017"></a>
<span id="l1018">                    } else if (handshaker.taskOutstanding()) {</span><a href="#l1018"></a>
<span id="l1019">                        hsStatus = HandshakeStatus.NEED_TASK;</span><a href="#l1019"></a>
<span id="l1020">                    }</span><a href="#l1020"></a>
<span id="l1021">                    break;</span><a href="#l1021"></a>
<span id="l1022"></span><a href="#l1022"></a>
<span id="l1023">                case Record.ct_application_data:</span><a href="#l1023"></a>
<span id="l1024">                    // Pass this right back up to the application.</span><a href="#l1024"></a>
<span id="l1025">                    if ((connectionState != cs_DATA)</span><a href="#l1025"></a>
<span id="l1026">                            &amp;&amp; (connectionState != cs_RENEGOTIATE)</span><a href="#l1026"></a>
<span id="l1027">                            &amp;&amp; (connectionState != cs_CLOSED)) {</span><a href="#l1027"></a>
<span id="l1028">                        throw new SSLProtocolException(</span><a href="#l1028"></a>
<span id="l1029">                            &quot;Data received in non-data state: &quot; +</span><a href="#l1029"></a>
<span id="l1030">                            connectionState);</span><a href="#l1030"></a>
<span id="l1031">                    }</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">                    if (expectingFinished) {</span><a href="#l1033"></a>
<span id="l1034">                        throw new SSLProtocolException</span><a href="#l1034"></a>
<span id="l1035">                                (&quot;Expecting finished message, received data&quot;);</span><a href="#l1035"></a>
<span id="l1036">                    }</span><a href="#l1036"></a>
<span id="l1037"></span><a href="#l1037"></a>
<span id="l1038">                    /*</span><a href="#l1038"></a>
<span id="l1039">                     * Don't return data once the inbound side is</span><a href="#l1039"></a>
<span id="l1040">                     * closed.</span><a href="#l1040"></a>
<span id="l1041">                     */</span><a href="#l1041"></a>
<span id="l1042">                    if (!inboundDone) {</span><a href="#l1042"></a>
<span id="l1043">                        ea.scatter(decryptedBB.slice());</span><a href="#l1043"></a>
<span id="l1044">                    }</span><a href="#l1044"></a>
<span id="l1045">                    break;</span><a href="#l1045"></a>
<span id="l1046"></span><a href="#l1046"></a>
<span id="l1047">                case Record.ct_alert:</span><a href="#l1047"></a>
<span id="l1048">                    recvAlert();</span><a href="#l1048"></a>
<span id="l1049">                    break;</span><a href="#l1049"></a>
<span id="l1050"></span><a href="#l1050"></a>
<span id="l1051">                case Record.ct_change_cipher_spec:</span><a href="#l1051"></a>
<span id="l1052">                    if ((connectionState != cs_HANDSHAKE</span><a href="#l1052"></a>
<span id="l1053">                                &amp;&amp; connectionState != cs_RENEGOTIATE)) {</span><a href="#l1053"></a>
<span id="l1054">                        // For the CCS message arriving in the wrong state</span><a href="#l1054"></a>
<span id="l1055">                        fatal(Alerts.alert_unexpected_message,</span><a href="#l1055"></a>
<span id="l1056">                                &quot;illegal change cipher spec msg, conn state = &quot;</span><a href="#l1056"></a>
<span id="l1057">                                + connectionState);</span><a href="#l1057"></a>
<span id="l1058">                    } else if (inputRecord.available() != 1</span><a href="#l1058"></a>
<span id="l1059">                            || inputRecord.read() != 1) {</span><a href="#l1059"></a>
<span id="l1060">                        // For structural/content issues with the CCS</span><a href="#l1060"></a>
<span id="l1061">                        fatal(Alerts.alert_unexpected_message,</span><a href="#l1061"></a>
<span id="l1062">                                &quot;Malformed change cipher spec msg&quot;);</span><a href="#l1062"></a>
<span id="l1063">                    }</span><a href="#l1063"></a>
<span id="l1064"></span><a href="#l1064"></a>
<span id="l1065">                    //</span><a href="#l1065"></a>
<span id="l1066">                    // The first message after a change_cipher_spec</span><a href="#l1066"></a>
<span id="l1067">                    // record MUST be a &quot;Finished&quot; handshake record,</span><a href="#l1067"></a>
<span id="l1068">                    // else it's a protocol violation.  We force this</span><a href="#l1068"></a>
<span id="l1069">                    // to be checked by a minor tweak to the state</span><a href="#l1069"></a>
<span id="l1070">                    // machine.</span><a href="#l1070"></a>
<span id="l1071">                    //</span><a href="#l1071"></a>
<span id="l1072">                    handshaker.receiveChangeCipherSpec();</span><a href="#l1072"></a>
<span id="l1073">                    changeReadCiphers();</span><a href="#l1073"></a>
<span id="l1074">                    // next message MUST be a finished message</span><a href="#l1074"></a>
<span id="l1075">                    expectingFinished = true;</span><a href="#l1075"></a>
<span id="l1076">                    break;</span><a href="#l1076"></a>
<span id="l1077"></span><a href="#l1077"></a>
<span id="l1078">                default:</span><a href="#l1078"></a>
<span id="l1079">                    //</span><a href="#l1079"></a>
<span id="l1080">                    // TLS requires that unrecognized records be ignored.</span><a href="#l1080"></a>
<span id="l1081">                    //</span><a href="#l1081"></a>
<span id="l1082">                    if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1082"></a>
<span id="l1083">                        System.out.println(threadName() +</span><a href="#l1083"></a>
<span id="l1084">                            &quot;, Received record type: &quot;</span><a href="#l1084"></a>
<span id="l1085">                            + inputRecord.contentType());</span><a href="#l1085"></a>
<span id="l1086">                    }</span><a href="#l1086"></a>
<span id="l1087">                    break;</span><a href="#l1087"></a>
<span id="l1088">                } // switch</span><a href="#l1088"></a>
<span id="l1089"></span><a href="#l1089"></a>
<span id="l1090">                /*</span><a href="#l1090"></a>
<span id="l1091">                 * We only need to check the sequence number state for</span><a href="#l1091"></a>
<span id="l1092">                 * non-handshaking record.</span><a href="#l1092"></a>
<span id="l1093">                 *</span><a href="#l1093"></a>
<span id="l1094">                 * Note that in order to maintain the handshake status</span><a href="#l1094"></a>
<span id="l1095">                 * properly, we check the sequence number after the last</span><a href="#l1095"></a>
<span id="l1096">                 * record reading process. As we request renegotiation</span><a href="#l1096"></a>
<span id="l1097">                 * or close the connection for wrapped sequence number</span><a href="#l1097"></a>
<span id="l1098">                 * when there is enough sequence number space left to</span><a href="#l1098"></a>
<span id="l1099">                 * handle a few more records, so the sequence number</span><a href="#l1099"></a>
<span id="l1100">                 * of the last record cannot be wrapped.</span><a href="#l1100"></a>
<span id="l1101">                 */</span><a href="#l1101"></a>
<span id="l1102">                if (connectionState &lt; cs_ERROR &amp;&amp; !isInboundDone() &amp;&amp;</span><a href="#l1102"></a>
<span id="l1103">                        (hsStatus == HandshakeStatus.NOT_HANDSHAKING)) {</span><a href="#l1103"></a>
<span id="l1104">                    if (checkSequenceNumber(readMAC,</span><a href="#l1104"></a>
<span id="l1105">                            inputRecord.contentType())) {</span><a href="#l1105"></a>
<span id="l1106">                        hsStatus = getHSStatus(null);</span><a href="#l1106"></a>
<span id="l1107">                    }</span><a href="#l1107"></a>
<span id="l1108">                }</span><a href="#l1108"></a>
<span id="l1109">            } // synchronized (this)</span><a href="#l1109"></a>
<span id="l1110">        }</span><a href="#l1110"></a>
<span id="l1111">        return hsStatus;</span><a href="#l1111"></a>
<span id="l1112">    }</span><a href="#l1112"></a>
<span id="l1113"></span><a href="#l1113"></a>
<span id="l1114"></span><a href="#l1114"></a>
<span id="l1115">    //</span><a href="#l1115"></a>
<span id="l1116">    // write/wrap side</span><a href="#l1116"></a>
<span id="l1117">    //</span><a href="#l1117"></a>
<span id="l1118"></span><a href="#l1118"></a>
<span id="l1119"></span><a href="#l1119"></a>
<span id="l1120">    /**</span><a href="#l1120"></a>
<span id="l1121">     * Wraps a buffer.  Does a variety of checks before grabbing</span><a href="#l1121"></a>
<span id="l1122">     * the wrapLock, which blocks multiple wraps from occurring.</span><a href="#l1122"></a>
<span id="l1123">     */</span><a href="#l1123"></a>
<span id="l1124">    public SSLEngineResult wrap(ByteBuffer [] appData,</span><a href="#l1124"></a>
<span id="l1125">            int offset, int length, ByteBuffer netData) throws SSLException {</span><a href="#l1125"></a>
<span id="l1126"></span><a href="#l1126"></a>
<span id="l1127">        EngineArgs ea = new EngineArgs(appData, offset, length, netData);</span><a href="#l1127"></a>
<span id="l1128"></span><a href="#l1128"></a>
<span id="l1129">        /*</span><a href="#l1129"></a>
<span id="l1130">         * We can be smarter about using smaller buffer sizes later.</span><a href="#l1130"></a>
<span id="l1131">         * For now, force it to be large enough to handle any</span><a href="#l1131"></a>
<span id="l1132">         * valid SSL/TLS record.</span><a href="#l1132"></a>
<span id="l1133">         */</span><a href="#l1133"></a>
<span id="l1134">        if (netData.remaining() &lt; EngineOutputRecord.maxRecordSize) {</span><a href="#l1134"></a>
<span id="l1135">            return new SSLEngineResult(</span><a href="#l1135"></a>
<span id="l1136">                Status.BUFFER_OVERFLOW, getHSStatus(null), 0, 0);</span><a href="#l1136"></a>
<span id="l1137">        }</span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139">        try {</span><a href="#l1139"></a>
<span id="l1140">            synchronized (wrapLock) {</span><a href="#l1140"></a>
<span id="l1141">                return writeAppRecord(ea);</span><a href="#l1141"></a>
<span id="l1142">            }</span><a href="#l1142"></a>
<span id="l1143">        } catch (SSLProtocolException spe) {</span><a href="#l1143"></a>
<span id="l1144">            // may be an unexpected handshake message</span><a href="#l1144"></a>
<span id="l1145">            fatal(Alerts.alert_unexpected_message, spe.getMessage(), spe);</span><a href="#l1145"></a>
<span id="l1146">            return null;  // make compiler happy</span><a href="#l1146"></a>
<span id="l1147">        } catch (Exception e) {</span><a href="#l1147"></a>
<span id="l1148">            ea.resetPos();</span><a href="#l1148"></a>
<span id="l1149"></span><a href="#l1149"></a>
<span id="l1150">            fatal(Alerts.alert_internal_error,</span><a href="#l1150"></a>
<span id="l1151">                &quot;problem wrapping app data&quot;, e);</span><a href="#l1151"></a>
<span id="l1152">            return null;  // make compiler happy</span><a href="#l1152"></a>
<span id="l1153">        } finally {</span><a href="#l1153"></a>
<span id="l1154">            /*</span><a href="#l1154"></a>
<span id="l1155">             * Just in case something didn't reset limits properly.</span><a href="#l1155"></a>
<span id="l1156">             */</span><a href="#l1156"></a>
<span id="l1157">            ea.resetLim();</span><a href="#l1157"></a>
<span id="l1158">        }</span><a href="#l1158"></a>
<span id="l1159">    }</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">    /*</span><a href="#l1161"></a>
<span id="l1162">     * Makes additional checks for unwrap, but this time more</span><a href="#l1162"></a>
<span id="l1163">     * specific to this packet and the current state of the machine.</span><a href="#l1163"></a>
<span id="l1164">     */</span><a href="#l1164"></a>
<span id="l1165">    private SSLEngineResult writeAppRecord(EngineArgs ea) throws IOException {</span><a href="#l1165"></a>
<span id="l1166"></span><a href="#l1166"></a>
<span id="l1167">        Status status = null;</span><a href="#l1167"></a>
<span id="l1168">        HandshakeStatus hsStatus = null;</span><a href="#l1168"></a>
<span id="l1169"></span><a href="#l1169"></a>
<span id="l1170">        /*</span><a href="#l1170"></a>
<span id="l1171">         * See if the handshaker needs to report back some SSLException.</span><a href="#l1171"></a>
<span id="l1172">         */</span><a href="#l1172"></a>
<span id="l1173">        checkTaskThrown();</span><a href="#l1173"></a>
<span id="l1174">        /*</span><a href="#l1174"></a>
<span id="l1175">         * short circuit if we're closed/closing.</span><a href="#l1175"></a>
<span id="l1176">         */</span><a href="#l1176"></a>
<span id="l1177">        if (writer.isOutboundDone()) {</span><a href="#l1177"></a>
<span id="l1178">            return new SSLEngineResult(Status.CLOSED, getHSStatus(null), 0, 0);</span><a href="#l1178"></a>
<span id="l1179">        }</span><a href="#l1179"></a>
<span id="l1180"></span><a href="#l1180"></a>
<span id="l1181">        /*</span><a href="#l1181"></a>
<span id="l1182">         * If we're still in cs_HANDSHAKE, make sure it's been</span><a href="#l1182"></a>
<span id="l1183">         * started.</span><a href="#l1183"></a>
<span id="l1184">         */</span><a href="#l1184"></a>
<span id="l1185">        synchronized (this) {</span><a href="#l1185"></a>
<span id="l1186">            if ((connectionState == cs_HANDSHAKE) ||</span><a href="#l1186"></a>
<span id="l1187">                    (connectionState == cs_START)) {</span><a href="#l1187"></a>
<span id="l1188">                kickstartHandshake();</span><a href="#l1188"></a>
<span id="l1189"></span><a href="#l1189"></a>
<span id="l1190">                /*</span><a href="#l1190"></a>
<span id="l1191">                 * If there's no HS data available to write, we can return</span><a href="#l1191"></a>
<span id="l1192">                 * without trying to wrap anything.</span><a href="#l1192"></a>
<span id="l1193">                 */</span><a href="#l1193"></a>
<span id="l1194">                hsStatus = getHSStatus(null);</span><a href="#l1194"></a>
<span id="l1195">                if (hsStatus == HandshakeStatus.NEED_UNWRAP) {</span><a href="#l1195"></a>
<span id="l1196">                    return new SSLEngineResult(Status.OK, hsStatus, 0, 0);</span><a href="#l1196"></a>
<span id="l1197">                }</span><a href="#l1197"></a>
<span id="l1198">            }</span><a href="#l1198"></a>
<span id="l1199">        }</span><a href="#l1199"></a>
<span id="l1200"></span><a href="#l1200"></a>
<span id="l1201">        /*</span><a href="#l1201"></a>
<span id="l1202">         * Grab a copy of this if it doesn't already exist,</span><a href="#l1202"></a>
<span id="l1203">         * and we can use it several places before anything major</span><a href="#l1203"></a>
<span id="l1204">         * happens on this side.  Races aren't critical</span><a href="#l1204"></a>
<span id="l1205">         * here.</span><a href="#l1205"></a>
<span id="l1206">         */</span><a href="#l1206"></a>
<span id="l1207">        if (hsStatus == null) {</span><a href="#l1207"></a>
<span id="l1208">            hsStatus = getHSStatus(null);</span><a href="#l1208"></a>
<span id="l1209">        }</span><a href="#l1209"></a>
<span id="l1210"></span><a href="#l1210"></a>
<span id="l1211">        /*</span><a href="#l1211"></a>
<span id="l1212">         * If we have a task outstanding, this *MUST* be done before</span><a href="#l1212"></a>
<span id="l1213">         * doing any more wrapping, because we could be in the middle</span><a href="#l1213"></a>
<span id="l1214">         * of receiving a handshake message, for example, a finished</span><a href="#l1214"></a>
<span id="l1215">         * message which would change the ciphers.</span><a href="#l1215"></a>
<span id="l1216">         */</span><a href="#l1216"></a>
<span id="l1217">        if (hsStatus == HandshakeStatus.NEED_TASK) {</span><a href="#l1217"></a>
<span id="l1218">            return new SSLEngineResult(</span><a href="#l1218"></a>
<span id="l1219">                Status.OK, hsStatus, 0, 0);</span><a href="#l1219"></a>
<span id="l1220">        }</span><a href="#l1220"></a>
<span id="l1221"></span><a href="#l1221"></a>
<span id="l1222">        /*</span><a href="#l1222"></a>
<span id="l1223">         * This will obtain any waiting outbound data, or will</span><a href="#l1223"></a>
<span id="l1224">         * process the outbound appData.</span><a href="#l1224"></a>
<span id="l1225">         */</span><a href="#l1225"></a>
<span id="l1226">        try {</span><a href="#l1226"></a>
<span id="l1227">            synchronized (writeLock) {</span><a href="#l1227"></a>
<span id="l1228">                hsStatus = writeRecord(outputRecord, ea);</span><a href="#l1228"></a>
<span id="l1229">            }</span><a href="#l1229"></a>
<span id="l1230">        } catch (SSLException e) {</span><a href="#l1230"></a>
<span id="l1231">            throw e;</span><a href="#l1231"></a>
<span id="l1232">        } catch (IOException e) {</span><a href="#l1232"></a>
<span id="l1233">            throw new SSLException(&quot;Write problems&quot;, e);</span><a href="#l1233"></a>
<span id="l1234">        }</span><a href="#l1234"></a>
<span id="l1235"></span><a href="#l1235"></a>
<span id="l1236">        /*</span><a href="#l1236"></a>
<span id="l1237">         * writeRecord might have reported some status.</span><a href="#l1237"></a>
<span id="l1238">         * Now check for the remaining cases.</span><a href="#l1238"></a>
<span id="l1239">         *</span><a href="#l1239"></a>
<span id="l1240">         * status above should cover:  NEED_WRAP/FINISHED</span><a href="#l1240"></a>
<span id="l1241">         */</span><a href="#l1241"></a>
<span id="l1242">        status = (isOutboundDone() ? Status.CLOSED : Status.OK);</span><a href="#l1242"></a>
<span id="l1243">        hsStatus = getHSStatus(hsStatus);</span><a href="#l1243"></a>
<span id="l1244"></span><a href="#l1244"></a>
<span id="l1245">        return new SSLEngineResult(status, hsStatus,</span><a href="#l1245"></a>
<span id="l1246">            ea.deltaApp(), ea.deltaNet());</span><a href="#l1246"></a>
<span id="l1247">    }</span><a href="#l1247"></a>
<span id="l1248"></span><a href="#l1248"></a>
<span id="l1249">    /*</span><a href="#l1249"></a>
<span id="l1250">     * Central point to write/get all of the outgoing data.</span><a href="#l1250"></a>
<span id="l1251">     */</span><a href="#l1251"></a>
<span id="l1252">    private HandshakeStatus writeRecord(EngineOutputRecord eor,</span><a href="#l1252"></a>
<span id="l1253">            EngineArgs ea) throws IOException {</span><a href="#l1253"></a>
<span id="l1254"></span><a href="#l1254"></a>
<span id="l1255">        // eventually compress as well.</span><a href="#l1255"></a>
<span id="l1256">        HandshakeStatus hsStatus =</span><a href="#l1256"></a>
<span id="l1257">                writer.writeRecord(eor, ea, writeMAC, writeCipher);</span><a href="#l1257"></a>
<span id="l1258"></span><a href="#l1258"></a>
<span id="l1259">        /*</span><a href="#l1259"></a>
<span id="l1260">         * We only need to check the sequence number state for</span><a href="#l1260"></a>
<span id="l1261">         * non-handshaking record.</span><a href="#l1261"></a>
<span id="l1262">         *</span><a href="#l1262"></a>
<span id="l1263">         * Note that in order to maintain the handshake status</span><a href="#l1263"></a>
<span id="l1264">         * properly, we check the sequence number after the last</span><a href="#l1264"></a>
<span id="l1265">         * record writing process. As we request renegotiation</span><a href="#l1265"></a>
<span id="l1266">         * or close the connection for wrapped sequence number</span><a href="#l1266"></a>
<span id="l1267">         * when there is enough sequence number space left to</span><a href="#l1267"></a>
<span id="l1268">         * handle a few more records, so the sequence number</span><a href="#l1268"></a>
<span id="l1269">         * of the last record cannot be wrapped.</span><a href="#l1269"></a>
<span id="l1270">         */</span><a href="#l1270"></a>
<span id="l1271">        if (connectionState &lt; cs_ERROR &amp;&amp; !isOutboundDone() &amp;&amp;</span><a href="#l1271"></a>
<span id="l1272">                (hsStatus == HandshakeStatus.NOT_HANDSHAKING)) {</span><a href="#l1272"></a>
<span id="l1273">            if (checkSequenceNumber(writeMAC, eor.contentType())) {</span><a href="#l1273"></a>
<span id="l1274">                hsStatus = getHSStatus(null);</span><a href="#l1274"></a>
<span id="l1275">            }</span><a href="#l1275"></a>
<span id="l1276">        }</span><a href="#l1276"></a>
<span id="l1277"></span><a href="#l1277"></a>
<span id="l1278">        /*</span><a href="#l1278"></a>
<span id="l1279">         * turn off the flag of the first application record if we really</span><a href="#l1279"></a>
<span id="l1280">         * consumed at least byte.</span><a href="#l1280"></a>
<span id="l1281">         */</span><a href="#l1281"></a>
<span id="l1282">        if (isFirstAppOutputRecord &amp;&amp; ea.deltaApp() &gt; 0) {</span><a href="#l1282"></a>
<span id="l1283">            isFirstAppOutputRecord = false;</span><a href="#l1283"></a>
<span id="l1284">        }</span><a href="#l1284"></a>
<span id="l1285"></span><a href="#l1285"></a>
<span id="l1286">        return hsStatus;</span><a href="#l1286"></a>
<span id="l1287">    }</span><a href="#l1287"></a>
<span id="l1288"></span><a href="#l1288"></a>
<span id="l1289">    /*</span><a href="#l1289"></a>
<span id="l1290">     * Need to split the payload except the following cases:</span><a href="#l1290"></a>
<span id="l1291">     *</span><a href="#l1291"></a>
<span id="l1292">     * 1. protocol version is TLS 1.1 or later;</span><a href="#l1292"></a>
<span id="l1293">     * 2. bulk cipher does not use CBC mode, including null bulk cipher suites.</span><a href="#l1293"></a>
<span id="l1294">     * 3. the payload is the first application record of a freshly</span><a href="#l1294"></a>
<span id="l1295">     *    negotiated TLS session.</span><a href="#l1295"></a>
<span id="l1296">     * 4. the CBC protection is disabled;</span><a href="#l1296"></a>
<span id="l1297">     *</span><a href="#l1297"></a>
<span id="l1298">     * More details, please refer to</span><a href="#l1298"></a>
<span id="l1299">     * EngineOutputRecord.write(EngineArgs, MAC, CipherBox).</span><a href="#l1299"></a>
<span id="l1300">     */</span><a href="#l1300"></a>
<span id="l1301">    boolean needToSplitPayload(CipherBox cipher, ProtocolVersion protocol) {</span><a href="#l1301"></a>
<span id="l1302">        return (protocol.v &lt;= ProtocolVersion.TLS10.v) &amp;&amp;</span><a href="#l1302"></a>
<span id="l1303">                cipher.isCBCMode() &amp;&amp; !isFirstAppOutputRecord &amp;&amp;</span><a href="#l1303"></a>
<span id="l1304">                Record.enableCBCProtection;</span><a href="#l1304"></a>
<span id="l1305">    }</span><a href="#l1305"></a>
<span id="l1306"></span><a href="#l1306"></a>
<span id="l1307">    /*</span><a href="#l1307"></a>
<span id="l1308">     * Non-application OutputRecords go through here.</span><a href="#l1308"></a>
<span id="l1309">     */</span><a href="#l1309"></a>
<span id="l1310">    void writeRecord(EngineOutputRecord eor) throws IOException {</span><a href="#l1310"></a>
<span id="l1311">        // eventually compress as well.</span><a href="#l1311"></a>
<span id="l1312">        writer.writeRecord(eor, writeMAC, writeCipher);</span><a href="#l1312"></a>
<span id="l1313"></span><a href="#l1313"></a>
<span id="l1314">        /*</span><a href="#l1314"></a>
<span id="l1315">         * Check the sequence number state</span><a href="#l1315"></a>
<span id="l1316">         *</span><a href="#l1316"></a>
<span id="l1317">         * Note that in order to maintain the connection I/O</span><a href="#l1317"></a>
<span id="l1318">         * properly, we check the sequence number after the last</span><a href="#l1318"></a>
<span id="l1319">         * record writing process. As we request renegotiation</span><a href="#l1319"></a>
<span id="l1320">         * or close the connection for wrapped sequence number</span><a href="#l1320"></a>
<span id="l1321">         * when there is enough sequence number space left to</span><a href="#l1321"></a>
<span id="l1322">         * handle a few more records, so the sequence number</span><a href="#l1322"></a>
<span id="l1323">         * of the last record cannot be wrapped.</span><a href="#l1323"></a>
<span id="l1324">         */</span><a href="#l1324"></a>
<span id="l1325">        if ((connectionState &lt; cs_ERROR) &amp;&amp; !isOutboundDone()) {</span><a href="#l1325"></a>
<span id="l1326">            checkSequenceNumber(writeMAC, eor.contentType());</span><a href="#l1326"></a>
<span id="l1327">        }</span><a href="#l1327"></a>
<span id="l1328">    }</span><a href="#l1328"></a>
<span id="l1329"></span><a href="#l1329"></a>
<span id="l1330">    //</span><a href="#l1330"></a>
<span id="l1331">    // Close code</span><a href="#l1331"></a>
<span id="l1332">    //</span><a href="#l1332"></a>
<span id="l1333"></span><a href="#l1333"></a>
<span id="l1334">    /**</span><a href="#l1334"></a>
<span id="l1335">     * Check the sequence number state</span><a href="#l1335"></a>
<span id="l1336">     *</span><a href="#l1336"></a>
<span id="l1337">     * RFC 4346 states that, &quot;Sequence numbers are of type uint64 and</span><a href="#l1337"></a>
<span id="l1338">     * may not exceed 2^64-1.  Sequence numbers do not wrap. If a TLS</span><a href="#l1338"></a>
<span id="l1339">     * implementation would need to wrap a sequence number, it must</span><a href="#l1339"></a>
<span id="l1340">     * renegotiate instead.&quot;</span><a href="#l1340"></a>
<span id="l1341">     *</span><a href="#l1341"></a>
<span id="l1342">     * Return true if the handshake status may be changed.</span><a href="#l1342"></a>
<span id="l1343">     */</span><a href="#l1343"></a>
<span id="l1344">    private boolean checkSequenceNumber(MAC mac, byte type)</span><a href="#l1344"></a>
<span id="l1345">            throws IOException {</span><a href="#l1345"></a>
<span id="l1346"></span><a href="#l1346"></a>
<span id="l1347">        /*</span><a href="#l1347"></a>
<span id="l1348">         * Don't bother to check the sequence number for error or</span><a href="#l1348"></a>
<span id="l1349">         * closed connections, or NULL MAC</span><a href="#l1349"></a>
<span id="l1350">         */</span><a href="#l1350"></a>
<span id="l1351">        if (connectionState &gt;= cs_ERROR || mac == MAC.NULL) {</span><a href="#l1351"></a>
<span id="l1352">            return false;</span><a href="#l1352"></a>
<span id="l1353">        }</span><a href="#l1353"></a>
<span id="l1354"></span><a href="#l1354"></a>
<span id="l1355">        /*</span><a href="#l1355"></a>
<span id="l1356">         * Conservatively, close the connection immediately when the</span><a href="#l1356"></a>
<span id="l1357">         * sequence number is close to overflow</span><a href="#l1357"></a>
<span id="l1358">         */</span><a href="#l1358"></a>
<span id="l1359">        if (mac.seqNumOverflow()) {</span><a href="#l1359"></a>
<span id="l1360">            /*</span><a href="#l1360"></a>
<span id="l1361">             * TLS protocols do not define a error alert for sequence</span><a href="#l1361"></a>
<span id="l1362">             * number overflow. We use handshake_failure error alert</span><a href="#l1362"></a>
<span id="l1363">             * for handshaking and bad_record_mac for other records.</span><a href="#l1363"></a>
<span id="l1364">             */</span><a href="#l1364"></a>
<span id="l1365">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1365"></a>
<span id="l1366">                System.out.println(threadName() +</span><a href="#l1366"></a>
<span id="l1367">                    &quot;, sequence number extremely close to overflow &quot; +</span><a href="#l1367"></a>
<span id="l1368">                    &quot;(2^64-1 packets). Closing connection.&quot;);</span><a href="#l1368"></a>
<span id="l1369">            }</span><a href="#l1369"></a>
<span id="l1370"></span><a href="#l1370"></a>
<span id="l1371">            fatal(Alerts.alert_handshake_failure, &quot;sequence number overflow&quot;);</span><a href="#l1371"></a>
<span id="l1372"></span><a href="#l1372"></a>
<span id="l1373">            return true; // make the compiler happy</span><a href="#l1373"></a>
<span id="l1374">        }</span><a href="#l1374"></a>
<span id="l1375"></span><a href="#l1375"></a>
<span id="l1376">        /*</span><a href="#l1376"></a>
<span id="l1377">         * Ask for renegotiation when need to renew sequence number.</span><a href="#l1377"></a>
<span id="l1378">         *</span><a href="#l1378"></a>
<span id="l1379">         * Don't bother to kickstart the renegotiation when the local is</span><a href="#l1379"></a>
<span id="l1380">         * asking for it.</span><a href="#l1380"></a>
<span id="l1381">         */</span><a href="#l1381"></a>
<span id="l1382">        if ((type != Record.ct_handshake) &amp;&amp; mac.seqNumIsHuge()) {</span><a href="#l1382"></a>
<span id="l1383">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1383"></a>
<span id="l1384">                System.out.println(threadName() + &quot;, request renegotiation &quot; +</span><a href="#l1384"></a>
<span id="l1385">                        &quot;to avoid sequence number overflow&quot;);</span><a href="#l1385"></a>
<span id="l1386">            }</span><a href="#l1386"></a>
<span id="l1387"></span><a href="#l1387"></a>
<span id="l1388">            beginHandshake();</span><a href="#l1388"></a>
<span id="l1389">            return true;</span><a href="#l1389"></a>
<span id="l1390">        }</span><a href="#l1390"></a>
<span id="l1391"></span><a href="#l1391"></a>
<span id="l1392">        return false;</span><a href="#l1392"></a>
<span id="l1393">    }</span><a href="#l1393"></a>
<span id="l1394"></span><a href="#l1394"></a>
<span id="l1395">    /**</span><a href="#l1395"></a>
<span id="l1396">     * Signals that no more outbound application data will be sent</span><a href="#l1396"></a>
<span id="l1397">     * on this &lt;code&gt;SSLEngine&lt;/code&gt;.</span><a href="#l1397"></a>
<span id="l1398">     */</span><a href="#l1398"></a>
<span id="l1399">    private void closeOutboundInternal() {</span><a href="#l1399"></a>
<span id="l1400"></span><a href="#l1400"></a>
<span id="l1401">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1401"></a>
<span id="l1402">            System.out.println(threadName() + &quot;, closeOutboundInternal()&quot;);</span><a href="#l1402"></a>
<span id="l1403">        }</span><a href="#l1403"></a>
<span id="l1404"></span><a href="#l1404"></a>
<span id="l1405">        /*</span><a href="#l1405"></a>
<span id="l1406">         * Already closed, ignore</span><a href="#l1406"></a>
<span id="l1407">         */</span><a href="#l1407"></a>
<span id="l1408">        if (writer.isOutboundDone()) {</span><a href="#l1408"></a>
<span id="l1409">            return;</span><a href="#l1409"></a>
<span id="l1410">        }</span><a href="#l1410"></a>
<span id="l1411"></span><a href="#l1411"></a>
<span id="l1412">        switch (connectionState) {</span><a href="#l1412"></a>
<span id="l1413"></span><a href="#l1413"></a>
<span id="l1414">        /*</span><a href="#l1414"></a>
<span id="l1415">         * If we haven't even started yet, don't bother reading inbound.</span><a href="#l1415"></a>
<span id="l1416">         */</span><a href="#l1416"></a>
<span id="l1417">        case cs_START:</span><a href="#l1417"></a>
<span id="l1418">            writer.closeOutbound();</span><a href="#l1418"></a>
<span id="l1419">            inboundDone = true;</span><a href="#l1419"></a>
<span id="l1420">            break;</span><a href="#l1420"></a>
<span id="l1421"></span><a href="#l1421"></a>
<span id="l1422">        case cs_ERROR:</span><a href="#l1422"></a>
<span id="l1423">        case cs_CLOSED:</span><a href="#l1423"></a>
<span id="l1424">            break;</span><a href="#l1424"></a>
<span id="l1425"></span><a href="#l1425"></a>
<span id="l1426">        /*</span><a href="#l1426"></a>
<span id="l1427">         * Otherwise we indicate clean termination.</span><a href="#l1427"></a>
<span id="l1428">         */</span><a href="#l1428"></a>
<span id="l1429">        // case cs_HANDSHAKE:</span><a href="#l1429"></a>
<span id="l1430">        // case cs_DATA:</span><a href="#l1430"></a>
<span id="l1431">        // case cs_RENEGOTIATE:</span><a href="#l1431"></a>
<span id="l1432">        default:</span><a href="#l1432"></a>
<span id="l1433">            warning(Alerts.alert_close_notify);</span><a href="#l1433"></a>
<span id="l1434">            writer.closeOutbound();</span><a href="#l1434"></a>
<span id="l1435">            break;</span><a href="#l1435"></a>
<span id="l1436">        }</span><a href="#l1436"></a>
<span id="l1437"></span><a href="#l1437"></a>
<span id="l1438">        // See comment in changeReadCiphers()</span><a href="#l1438"></a>
<span id="l1439">        writeCipher.dispose();</span><a href="#l1439"></a>
<span id="l1440"></span><a href="#l1440"></a>
<span id="l1441">        connectionState = cs_CLOSED;</span><a href="#l1441"></a>
<span id="l1442">    }</span><a href="#l1442"></a>
<span id="l1443"></span><a href="#l1443"></a>
<span id="l1444">    synchronized public void closeOutbound() {</span><a href="#l1444"></a>
<span id="l1445">        /*</span><a href="#l1445"></a>
<span id="l1446">         * Dump out a close_notify to the remote side</span><a href="#l1446"></a>
<span id="l1447">         */</span><a href="#l1447"></a>
<span id="l1448">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1448"></a>
<span id="l1449">            System.out.println(threadName() + &quot;, called closeOutbound()&quot;);</span><a href="#l1449"></a>
<span id="l1450">        }</span><a href="#l1450"></a>
<span id="l1451"></span><a href="#l1451"></a>
<span id="l1452">        closeOutboundInternal();</span><a href="#l1452"></a>
<span id="l1453">    }</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">    /**</span><a href="#l1455"></a>
<span id="l1456">     * Returns the outbound application data closure state</span><a href="#l1456"></a>
<span id="l1457">     */</span><a href="#l1457"></a>
<span id="l1458">    public boolean isOutboundDone() {</span><a href="#l1458"></a>
<span id="l1459">        return writer.isOutboundDone();</span><a href="#l1459"></a>
<span id="l1460">    }</span><a href="#l1460"></a>
<span id="l1461"></span><a href="#l1461"></a>
<span id="l1462">    /**</span><a href="#l1462"></a>
<span id="l1463">     * Signals that no more inbound network data will be sent</span><a href="#l1463"></a>
<span id="l1464">     * to this &lt;code&gt;SSLEngine&lt;/code&gt;.</span><a href="#l1464"></a>
<span id="l1465">     */</span><a href="#l1465"></a>
<span id="l1466">    private void closeInboundInternal() {</span><a href="#l1466"></a>
<span id="l1467"></span><a href="#l1467"></a>
<span id="l1468">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1468"></a>
<span id="l1469">            System.out.println(threadName() + &quot;, closeInboundInternal()&quot;);</span><a href="#l1469"></a>
<span id="l1470">        }</span><a href="#l1470"></a>
<span id="l1471"></span><a href="#l1471"></a>
<span id="l1472">        /*</span><a href="#l1472"></a>
<span id="l1473">         * Already closed, ignore</span><a href="#l1473"></a>
<span id="l1474">         */</span><a href="#l1474"></a>
<span id="l1475">        if (inboundDone) {</span><a href="#l1475"></a>
<span id="l1476">            return;</span><a href="#l1476"></a>
<span id="l1477">        }</span><a href="#l1477"></a>
<span id="l1478"></span><a href="#l1478"></a>
<span id="l1479">        closeOutboundInternal();</span><a href="#l1479"></a>
<span id="l1480">        inboundDone = true;</span><a href="#l1480"></a>
<span id="l1481"></span><a href="#l1481"></a>
<span id="l1482">        // See comment in changeReadCiphers()</span><a href="#l1482"></a>
<span id="l1483">        readCipher.dispose();</span><a href="#l1483"></a>
<span id="l1484"></span><a href="#l1484"></a>
<span id="l1485">        connectionState = cs_CLOSED;</span><a href="#l1485"></a>
<span id="l1486">    }</span><a href="#l1486"></a>
<span id="l1487"></span><a href="#l1487"></a>
<span id="l1488">    /*</span><a href="#l1488"></a>
<span id="l1489">     * Close the inbound side of the connection.  We grab the</span><a href="#l1489"></a>
<span id="l1490">     * lock here, and do the real work in the internal verison.</span><a href="#l1490"></a>
<span id="l1491">     * We do check for truncation attacks.</span><a href="#l1491"></a>
<span id="l1492">     */</span><a href="#l1492"></a>
<span id="l1493">    synchronized public void closeInbound() throws SSLException {</span><a href="#l1493"></a>
<span id="l1494">        /*</span><a href="#l1494"></a>
<span id="l1495">         * Currently closes the outbound side as well.  The IETF TLS</span><a href="#l1495"></a>
<span id="l1496">         * working group has expressed the opinion that 1/2 open</span><a href="#l1496"></a>
<span id="l1497">         * connections are not allowed by the spec.  May change</span><a href="#l1497"></a>
<span id="l1498">         * someday in the future.</span><a href="#l1498"></a>
<span id="l1499">         */</span><a href="#l1499"></a>
<span id="l1500">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1500"></a>
<span id="l1501">            System.out.println(threadName() + &quot;, called closeInbound()&quot;);</span><a href="#l1501"></a>
<span id="l1502">        }</span><a href="#l1502"></a>
<span id="l1503"></span><a href="#l1503"></a>
<span id="l1504">        /*</span><a href="#l1504"></a>
<span id="l1505">         * No need to throw an Exception if we haven't even started yet.</span><a href="#l1505"></a>
<span id="l1506">         */</span><a href="#l1506"></a>
<span id="l1507">        if ((connectionState != cs_START) &amp;&amp; !recvCN) {</span><a href="#l1507"></a>
<span id="l1508">            recvCN = true;  // Only receive the Exception once</span><a href="#l1508"></a>
<span id="l1509">            fatal(Alerts.alert_internal_error,</span><a href="#l1509"></a>
<span id="l1510">                &quot;Inbound closed before receiving peer's close_notify: &quot; +</span><a href="#l1510"></a>
<span id="l1511">                &quot;possible truncation attack?&quot;);</span><a href="#l1511"></a>
<span id="l1512">        } else {</span><a href="#l1512"></a>
<span id="l1513">            /*</span><a href="#l1513"></a>
<span id="l1514">             * Currently, this is a no-op, but in case we change</span><a href="#l1514"></a>
<span id="l1515">             * the close inbound code later.</span><a href="#l1515"></a>
<span id="l1516">             */</span><a href="#l1516"></a>
<span id="l1517">            closeInboundInternal();</span><a href="#l1517"></a>
<span id="l1518">        }</span><a href="#l1518"></a>
<span id="l1519">    }</span><a href="#l1519"></a>
<span id="l1520"></span><a href="#l1520"></a>
<span id="l1521">    /**</span><a href="#l1521"></a>
<span id="l1522">     * Returns the network inbound data closure state</span><a href="#l1522"></a>
<span id="l1523">     */</span><a href="#l1523"></a>
<span id="l1524">    synchronized public boolean isInboundDone() {</span><a href="#l1524"></a>
<span id="l1525">        return inboundDone;</span><a href="#l1525"></a>
<span id="l1526">    }</span><a href="#l1526"></a>
<span id="l1527"></span><a href="#l1527"></a>
<span id="l1528"></span><a href="#l1528"></a>
<span id="l1529">    //</span><a href="#l1529"></a>
<span id="l1530">    // Misc stuff</span><a href="#l1530"></a>
<span id="l1531">    //</span><a href="#l1531"></a>
<span id="l1532"></span><a href="#l1532"></a>
<span id="l1533"></span><a href="#l1533"></a>
<span id="l1534">    /**</span><a href="#l1534"></a>
<span id="l1535">     * Returns the current &lt;code&gt;SSLSession&lt;/code&gt; for this</span><a href="#l1535"></a>
<span id="l1536">     * &lt;code&gt;SSLEngine&lt;/code&gt;</span><a href="#l1536"></a>
<span id="l1537">     * &lt;P&gt;</span><a href="#l1537"></a>
<span id="l1538">     * These can be long lived, and frequently correspond to an</span><a href="#l1538"></a>
<span id="l1539">     * entire login session for some user.</span><a href="#l1539"></a>
<span id="l1540">     */</span><a href="#l1540"></a>
<span id="l1541">    synchronized public SSLSession getSession() {</span><a href="#l1541"></a>
<span id="l1542">        return sess;</span><a href="#l1542"></a>
<span id="l1543">    }</span><a href="#l1543"></a>
<span id="l1544"></span><a href="#l1544"></a>
<span id="l1545">    @Override</span><a href="#l1545"></a>
<span id="l1546">    synchronized public SSLSession getHandshakeSession() {</span><a href="#l1546"></a>
<span id="l1547">        return handshakeSession;</span><a href="#l1547"></a>
<span id="l1548">    }</span><a href="#l1548"></a>
<span id="l1549"></span><a href="#l1549"></a>
<span id="l1550">    synchronized void setHandshakeSession(SSLSessionImpl session) {</span><a href="#l1550"></a>
<span id="l1551">        handshakeSession = session;</span><a href="#l1551"></a>
<span id="l1552">    }</span><a href="#l1552"></a>
<span id="l1553"></span><a href="#l1553"></a>
<span id="l1554">    /**</span><a href="#l1554"></a>
<span id="l1555">     * Returns a delegated &lt;code&gt;Runnable&lt;/code&gt; task for</span><a href="#l1555"></a>
<span id="l1556">     * this &lt;code&gt;SSLEngine&lt;/code&gt;.</span><a href="#l1556"></a>
<span id="l1557">     */</span><a href="#l1557"></a>
<span id="l1558">    synchronized public Runnable getDelegatedTask() {</span><a href="#l1558"></a>
<span id="l1559">        if (handshaker != null) {</span><a href="#l1559"></a>
<span id="l1560">            return handshaker.getTask();</span><a href="#l1560"></a>
<span id="l1561">        }</span><a href="#l1561"></a>
<span id="l1562">        return null;</span><a href="#l1562"></a>
<span id="l1563">    }</span><a href="#l1563"></a>
<span id="l1564"></span><a href="#l1564"></a>
<span id="l1565"></span><a href="#l1565"></a>
<span id="l1566">    //</span><a href="#l1566"></a>
<span id="l1567">    // EXCEPTION AND ALERT HANDLING</span><a href="#l1567"></a>
<span id="l1568">    //</span><a href="#l1568"></a>
<span id="l1569"></span><a href="#l1569"></a>
<span id="l1570">    /*</span><a href="#l1570"></a>
<span id="l1571">     * Send a warning alert.</span><a href="#l1571"></a>
<span id="l1572">     */</span><a href="#l1572"></a>
<span id="l1573">    void warning(byte description) {</span><a href="#l1573"></a>
<span id="l1574">        sendAlert(Alerts.alert_warning, description);</span><a href="#l1574"></a>
<span id="l1575">    }</span><a href="#l1575"></a>
<span id="l1576"></span><a href="#l1576"></a>
<span id="l1577">    synchronized void fatal(byte description, String diagnostic)</span><a href="#l1577"></a>
<span id="l1578">            throws SSLException {</span><a href="#l1578"></a>
<span id="l1579">        fatal(description, diagnostic, null);</span><a href="#l1579"></a>
<span id="l1580">    }</span><a href="#l1580"></a>
<span id="l1581"></span><a href="#l1581"></a>
<span id="l1582">    synchronized void fatal(byte description, Throwable cause)</span><a href="#l1582"></a>
<span id="l1583">            throws SSLException {</span><a href="#l1583"></a>
<span id="l1584">        fatal(description, null, cause);</span><a href="#l1584"></a>
<span id="l1585">    }</span><a href="#l1585"></a>
<span id="l1586"></span><a href="#l1586"></a>
<span id="l1587">    /*</span><a href="#l1587"></a>
<span id="l1588">     * We've got a fatal error here, so start the shutdown process.</span><a href="#l1588"></a>
<span id="l1589">     *</span><a href="#l1589"></a>
<span id="l1590">     * Because of the way the code was written, we have some code</span><a href="#l1590"></a>
<span id="l1591">     * calling fatal directly when the &quot;description&quot; is known</span><a href="#l1591"></a>
<span id="l1592">     * and some throwing Exceptions which are then caught by higher</span><a href="#l1592"></a>
<span id="l1593">     * levels which then call here.  This code needs to determine</span><a href="#l1593"></a>
<span id="l1594">     * if one of the lower levels has already started the process.</span><a href="#l1594"></a>
<span id="l1595">     *</span><a href="#l1595"></a>
<span id="l1596">     * We won't worry about Error's, if we have one of those,</span><a href="#l1596"></a>
<span id="l1597">     * we're in worse trouble.  Note:  the networking code doesn't</span><a href="#l1597"></a>
<span id="l1598">     * deal with Errors either.</span><a href="#l1598"></a>
<span id="l1599">     */</span><a href="#l1599"></a>
<span id="l1600">    synchronized void fatal(byte description, String diagnostic,</span><a href="#l1600"></a>
<span id="l1601">            Throwable cause) throws SSLException {</span><a href="#l1601"></a>
<span id="l1602"></span><a href="#l1602"></a>
<span id="l1603">        /*</span><a href="#l1603"></a>
<span id="l1604">         * If we have no further information, make a general-purpose</span><a href="#l1604"></a>
<span id="l1605">         * message for folks to see.  We generally have one or the other.</span><a href="#l1605"></a>
<span id="l1606">         */</span><a href="#l1606"></a>
<span id="l1607">        if (diagnostic == null) {</span><a href="#l1607"></a>
<span id="l1608">            diagnostic = &quot;General SSLEngine problem&quot;;</span><a href="#l1608"></a>
<span id="l1609">        }</span><a href="#l1609"></a>
<span id="l1610">        if (cause == null) {</span><a href="#l1610"></a>
<span id="l1611">            cause = Alerts.getSSLException(description, cause, diagnostic);</span><a href="#l1611"></a>
<span id="l1612">        }</span><a href="#l1612"></a>
<span id="l1613"></span><a href="#l1613"></a>
<span id="l1614">        /*</span><a href="#l1614"></a>
<span id="l1615">         * If we've already shutdown because of an error,</span><a href="#l1615"></a>
<span id="l1616">         * there is nothing we can do except rethrow the exception.</span><a href="#l1616"></a>
<span id="l1617">         *</span><a href="#l1617"></a>
<span id="l1618">         * Most exceptions seen here will be SSLExceptions.</span><a href="#l1618"></a>
<span id="l1619">         * We may find the occasional Exception which hasn't been</span><a href="#l1619"></a>
<span id="l1620">         * converted to a SSLException, so we'll do it here.</span><a href="#l1620"></a>
<span id="l1621">         */</span><a href="#l1621"></a>
<span id="l1622">        if (closeReason != null) {</span><a href="#l1622"></a>
<span id="l1623">            if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1623"></a>
<span id="l1624">                System.out.println(threadName() +</span><a href="#l1624"></a>
<span id="l1625">                    &quot;, fatal: engine already closed.  Rethrowing &quot; +</span><a href="#l1625"></a>
<span id="l1626">                    cause.toString());</span><a href="#l1626"></a>
<span id="l1627">            }</span><a href="#l1627"></a>
<span id="l1628">            if (cause instanceof RuntimeException) {</span><a href="#l1628"></a>
<span id="l1629">                throw (RuntimeException)cause;</span><a href="#l1629"></a>
<span id="l1630">            } else if (cause instanceof SSLException) {</span><a href="#l1630"></a>
<span id="l1631">                throw (SSLException)cause;</span><a href="#l1631"></a>
<span id="l1632">            } else if (cause instanceof Exception) {</span><a href="#l1632"></a>
<span id="l1633">                throw new SSLException(&quot;fatal SSLEngine condition&quot;, cause);</span><a href="#l1633"></a>
<span id="l1634">            }</span><a href="#l1634"></a>
<span id="l1635">        }</span><a href="#l1635"></a>
<span id="l1636"></span><a href="#l1636"></a>
<span id="l1637">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1637"></a>
<span id="l1638">            System.out.println(threadName()</span><a href="#l1638"></a>
<span id="l1639">                        + &quot;, fatal error: &quot; + description +</span><a href="#l1639"></a>
<span id="l1640">                        &quot;: &quot; + diagnostic + &quot;\n&quot; + cause.toString());</span><a href="#l1640"></a>
<span id="l1641">        }</span><a href="#l1641"></a>
<span id="l1642"></span><a href="#l1642"></a>
<span id="l1643">        /*</span><a href="#l1643"></a>
<span id="l1644">         * Ok, this engine's going down.</span><a href="#l1644"></a>
<span id="l1645">         */</span><a href="#l1645"></a>
<span id="l1646">        int oldState = connectionState;</span><a href="#l1646"></a>
<span id="l1647">        connectionState = cs_ERROR;</span><a href="#l1647"></a>
<span id="l1648"></span><a href="#l1648"></a>
<span id="l1649">        inboundDone = true;</span><a href="#l1649"></a>
<span id="l1650"></span><a href="#l1650"></a>
<span id="l1651">        sess.invalidate();</span><a href="#l1651"></a>
<span id="l1652">        if (handshakeSession != null) {</span><a href="#l1652"></a>
<span id="l1653">            handshakeSession.invalidate();</span><a href="#l1653"></a>
<span id="l1654">        }</span><a href="#l1654"></a>
<span id="l1655"></span><a href="#l1655"></a>
<span id="l1656">        /*</span><a href="#l1656"></a>
<span id="l1657">         * If we haven't even started handshaking yet, no need</span><a href="#l1657"></a>
<span id="l1658">         * to generate the fatal close alert.</span><a href="#l1658"></a>
<span id="l1659">         */</span><a href="#l1659"></a>
<span id="l1660">        if (oldState != cs_START) {</span><a href="#l1660"></a>
<span id="l1661">            sendAlert(Alerts.alert_fatal, description);</span><a href="#l1661"></a>
<span id="l1662">        }</span><a href="#l1662"></a>
<span id="l1663"></span><a href="#l1663"></a>
<span id="l1664">        if (cause instanceof SSLException) { // only true if != null</span><a href="#l1664"></a>
<span id="l1665">            closeReason = (SSLException)cause;</span><a href="#l1665"></a>
<span id="l1666">        } else {</span><a href="#l1666"></a>
<span id="l1667">            /*</span><a href="#l1667"></a>
<span id="l1668">             * Including RuntimeExceptions, but we'll throw those</span><a href="#l1668"></a>
<span id="l1669">             * down below.  The closeReason isn't used again,</span><a href="#l1669"></a>
<span id="l1670">             * except for null checks.</span><a href="#l1670"></a>
<span id="l1671">             */</span><a href="#l1671"></a>
<span id="l1672">            closeReason =</span><a href="#l1672"></a>
<span id="l1673">                Alerts.getSSLException(description, cause, diagnostic);</span><a href="#l1673"></a>
<span id="l1674">        }</span><a href="#l1674"></a>
<span id="l1675"></span><a href="#l1675"></a>
<span id="l1676">        writer.closeOutbound();</span><a href="#l1676"></a>
<span id="l1677"></span><a href="#l1677"></a>
<span id="l1678">        connectionState = cs_CLOSED;</span><a href="#l1678"></a>
<span id="l1679"></span><a href="#l1679"></a>
<span id="l1680">        // See comment in changeReadCiphers()</span><a href="#l1680"></a>
<span id="l1681">        readCipher.dispose();</span><a href="#l1681"></a>
<span id="l1682">        writeCipher.dispose();</span><a href="#l1682"></a>
<span id="l1683"></span><a href="#l1683"></a>
<span id="l1684">        if (cause instanceof RuntimeException) {</span><a href="#l1684"></a>
<span id="l1685">            throw (RuntimeException)cause;</span><a href="#l1685"></a>
<span id="l1686">        } else {</span><a href="#l1686"></a>
<span id="l1687">            throw closeReason;</span><a href="#l1687"></a>
<span id="l1688">        }</span><a href="#l1688"></a>
<span id="l1689">    }</span><a href="#l1689"></a>
<span id="l1690"></span><a href="#l1690"></a>
<span id="l1691">    /*</span><a href="#l1691"></a>
<span id="l1692">     * Process an incoming alert ... caller must already have synchronized</span><a href="#l1692"></a>
<span id="l1693">     * access to &quot;this&quot;.</span><a href="#l1693"></a>
<span id="l1694">     */</span><a href="#l1694"></a>
<span id="l1695">    private void recvAlert() throws IOException {</span><a href="#l1695"></a>
<span id="l1696">        byte level = (byte)inputRecord.read();</span><a href="#l1696"></a>
<span id="l1697">        byte description = (byte)inputRecord.read();</span><a href="#l1697"></a>
<span id="l1698">        if (description == -1) { // check for short message</span><a href="#l1698"></a>
<span id="l1699">            fatal(Alerts.alert_illegal_parameter, &quot;Short alert message&quot;);</span><a href="#l1699"></a>
<span id="l1700">        }</span><a href="#l1700"></a>
<span id="l1701"></span><a href="#l1701"></a>
<span id="l1702">        if (debug != null &amp;&amp; (Debug.isOn(&quot;record&quot;) ||</span><a href="#l1702"></a>
<span id="l1703">                Debug.isOn(&quot;handshake&quot;))) {</span><a href="#l1703"></a>
<span id="l1704">            synchronized (System.out) {</span><a href="#l1704"></a>
<span id="l1705">                System.out.print(threadName());</span><a href="#l1705"></a>
<span id="l1706">                System.out.print(&quot;, RECV &quot; + protocolVersion + &quot; ALERT:  &quot;);</span><a href="#l1706"></a>
<span id="l1707">                if (level == Alerts.alert_fatal) {</span><a href="#l1707"></a>
<span id="l1708">                    System.out.print(&quot;fatal, &quot;);</span><a href="#l1708"></a>
<span id="l1709">                } else if (level == Alerts.alert_warning) {</span><a href="#l1709"></a>
<span id="l1710">                    System.out.print(&quot;warning, &quot;);</span><a href="#l1710"></a>
<span id="l1711">                } else {</span><a href="#l1711"></a>
<span id="l1712">                    System.out.print(&quot;&lt;level &quot; + (0x0ff &amp; level) + &quot;&gt;, &quot;);</span><a href="#l1712"></a>
<span id="l1713">                }</span><a href="#l1713"></a>
<span id="l1714">                System.out.println(Alerts.alertDescription(description));</span><a href="#l1714"></a>
<span id="l1715">            }</span><a href="#l1715"></a>
<span id="l1716">        }</span><a href="#l1716"></a>
<span id="l1717"></span><a href="#l1717"></a>
<span id="l1718">        if (level == Alerts.alert_warning) {</span><a href="#l1718"></a>
<span id="l1719">            if (description == Alerts.alert_close_notify) {</span><a href="#l1719"></a>
<span id="l1720">                if (connectionState == cs_HANDSHAKE) {</span><a href="#l1720"></a>
<span id="l1721">                    fatal(Alerts.alert_unexpected_message,</span><a href="#l1721"></a>
<span id="l1722">                                &quot;Received close_notify during handshake&quot;);</span><a href="#l1722"></a>
<span id="l1723">                } else {</span><a href="#l1723"></a>
<span id="l1724">                    recvCN = true;</span><a href="#l1724"></a>
<span id="l1725">                    closeInboundInternal();  // reply to close</span><a href="#l1725"></a>
<span id="l1726">                }</span><a href="#l1726"></a>
<span id="l1727">            } else {</span><a href="#l1727"></a>
<span id="l1728"></span><a href="#l1728"></a>
<span id="l1729">                //</span><a href="#l1729"></a>
<span id="l1730">                // The other legal warnings relate to certificates,</span><a href="#l1730"></a>
<span id="l1731">                // e.g. no_certificate, bad_certificate, etc; these</span><a href="#l1731"></a>
<span id="l1732">                // are important to the handshaking code, which can</span><a href="#l1732"></a>
<span id="l1733">                // also handle illegal protocol alerts if needed.</span><a href="#l1733"></a>
<span id="l1734">                //</span><a href="#l1734"></a>
<span id="l1735">                if (handshaker != null) {</span><a href="#l1735"></a>
<span id="l1736">                    handshaker.handshakeAlert(description);</span><a href="#l1736"></a>
<span id="l1737">                }</span><a href="#l1737"></a>
<span id="l1738">            }</span><a href="#l1738"></a>
<span id="l1739">        } else { // fatal or unknown level</span><a href="#l1739"></a>
<span id="l1740">            String reason = &quot;Received fatal alert: &quot;</span><a href="#l1740"></a>
<span id="l1741">                + Alerts.alertDescription(description);</span><a href="#l1741"></a>
<span id="l1742">            if (closeReason == null) {</span><a href="#l1742"></a>
<span id="l1743">                closeReason = Alerts.getSSLException(description, reason);</span><a href="#l1743"></a>
<span id="l1744">            }</span><a href="#l1744"></a>
<span id="l1745">            fatal(Alerts.alert_unexpected_message, reason);</span><a href="#l1745"></a>
<span id="l1746">        }</span><a href="#l1746"></a>
<span id="l1747">    }</span><a href="#l1747"></a>
<span id="l1748"></span><a href="#l1748"></a>
<span id="l1749"></span><a href="#l1749"></a>
<span id="l1750">    /*</span><a href="#l1750"></a>
<span id="l1751">     * Emit alerts.  Caller must have synchronized with &quot;this&quot;.</span><a href="#l1751"></a>
<span id="l1752">     */</span><a href="#l1752"></a>
<span id="l1753">    private void sendAlert(byte level, byte description) {</span><a href="#l1753"></a>
<span id="l1754">        // the connectionState cannot be cs_START</span><a href="#l1754"></a>
<span id="l1755">        if (connectionState &gt;= cs_CLOSED) {</span><a href="#l1755"></a>
<span id="l1756">            return;</span><a href="#l1756"></a>
<span id="l1757">        }</span><a href="#l1757"></a>
<span id="l1758"></span><a href="#l1758"></a>
<span id="l1759">        // For initial handshaking, don't send alert message to peer if</span><a href="#l1759"></a>
<span id="l1760">        // handshaker has not started.</span><a href="#l1760"></a>
<span id="l1761">        if (connectionState == cs_HANDSHAKE &amp;&amp;</span><a href="#l1761"></a>
<span id="l1762">            (handshaker == null || !handshaker.started())) {</span><a href="#l1762"></a>
<span id="l1763">            return;</span><a href="#l1763"></a>
<span id="l1764">        }</span><a href="#l1764"></a>
<span id="l1765"></span><a href="#l1765"></a>
<span id="l1766">        EngineOutputRecord r = new EngineOutputRecord(Record.ct_alert, this);</span><a href="#l1766"></a>
<span id="l1767">        r.setVersion(protocolVersion);</span><a href="#l1767"></a>
<span id="l1768"></span><a href="#l1768"></a>
<span id="l1769">        boolean useDebug = debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;);</span><a href="#l1769"></a>
<span id="l1770">        if (useDebug) {</span><a href="#l1770"></a>
<span id="l1771">            synchronized (System.out) {</span><a href="#l1771"></a>
<span id="l1772">                System.out.print(threadName());</span><a href="#l1772"></a>
<span id="l1773">                System.out.print(&quot;, SEND &quot; + protocolVersion + &quot; ALERT:  &quot;);</span><a href="#l1773"></a>
<span id="l1774">                if (level == Alerts.alert_fatal) {</span><a href="#l1774"></a>
<span id="l1775">                    System.out.print(&quot;fatal, &quot;);</span><a href="#l1775"></a>
<span id="l1776">                } else if (level == Alerts.alert_warning) {</span><a href="#l1776"></a>
<span id="l1777">                    System.out.print(&quot;warning, &quot;);</span><a href="#l1777"></a>
<span id="l1778">                } else {</span><a href="#l1778"></a>
<span id="l1779">                    System.out.print(&quot;&lt;level = &quot; + (0x0ff &amp; level) + &quot;&gt;, &quot;);</span><a href="#l1779"></a>
<span id="l1780">                }</span><a href="#l1780"></a>
<span id="l1781">                System.out.println(&quot;description = &quot;</span><a href="#l1781"></a>
<span id="l1782">                        + Alerts.alertDescription(description));</span><a href="#l1782"></a>
<span id="l1783">            }</span><a href="#l1783"></a>
<span id="l1784">        }</span><a href="#l1784"></a>
<span id="l1785"></span><a href="#l1785"></a>
<span id="l1786">        r.write(level);</span><a href="#l1786"></a>
<span id="l1787">        r.write(description);</span><a href="#l1787"></a>
<span id="l1788">        try {</span><a href="#l1788"></a>
<span id="l1789">            writeRecord(r);</span><a href="#l1789"></a>
<span id="l1790">        } catch (IOException e) {</span><a href="#l1790"></a>
<span id="l1791">            if (useDebug) {</span><a href="#l1791"></a>
<span id="l1792">                System.out.println(threadName() +</span><a href="#l1792"></a>
<span id="l1793">                    &quot;, Exception sending alert: &quot; + e);</span><a href="#l1793"></a>
<span id="l1794">            }</span><a href="#l1794"></a>
<span id="l1795">        }</span><a href="#l1795"></a>
<span id="l1796">    }</span><a href="#l1796"></a>
<span id="l1797"></span><a href="#l1797"></a>
<span id="l1798"></span><a href="#l1798"></a>
<span id="l1799">    //</span><a href="#l1799"></a>
<span id="l1800">    // VARIOUS OTHER METHODS (COMMON TO SSLSocket)</span><a href="#l1800"></a>
<span id="l1801">    //</span><a href="#l1801"></a>
<span id="l1802"></span><a href="#l1802"></a>
<span id="l1803"></span><a href="#l1803"></a>
<span id="l1804">    /**</span><a href="#l1804"></a>
<span id="l1805">     * Controls whether new connections may cause creation of new SSL</span><a href="#l1805"></a>
<span id="l1806">     * sessions.</span><a href="#l1806"></a>
<span id="l1807">     *</span><a href="#l1807"></a>
<span id="l1808">     * As long as handshaking has not started, we can change</span><a href="#l1808"></a>
<span id="l1809">     * whether we enable session creations.  Otherwise,</span><a href="#l1809"></a>
<span id="l1810">     * we will need to wait for the next handshake.</span><a href="#l1810"></a>
<span id="l1811">     */</span><a href="#l1811"></a>
<span id="l1812">    synchronized public void setEnableSessionCreation(boolean flag) {</span><a href="#l1812"></a>
<span id="l1813">        enableSessionCreation = flag;</span><a href="#l1813"></a>
<span id="l1814"></span><a href="#l1814"></a>
<span id="l1815">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l1815"></a>
<span id="l1816">            handshaker.setEnableSessionCreation(enableSessionCreation);</span><a href="#l1816"></a>
<span id="l1817">        }</span><a href="#l1817"></a>
<span id="l1818">    }</span><a href="#l1818"></a>
<span id="l1819"></span><a href="#l1819"></a>
<span id="l1820">    /**</span><a href="#l1820"></a>
<span id="l1821">     * Returns true if new connections may cause creation of new SSL</span><a href="#l1821"></a>
<span id="l1822">     * sessions.</span><a href="#l1822"></a>
<span id="l1823">     */</span><a href="#l1823"></a>
<span id="l1824">    synchronized public boolean getEnableSessionCreation() {</span><a href="#l1824"></a>
<span id="l1825">        return enableSessionCreation;</span><a href="#l1825"></a>
<span id="l1826">    }</span><a href="#l1826"></a>
<span id="l1827"></span><a href="#l1827"></a>
<span id="l1828"></span><a href="#l1828"></a>
<span id="l1829">    /**</span><a href="#l1829"></a>
<span id="l1830">     * Sets the flag controlling whether a server mode engine</span><a href="#l1830"></a>
<span id="l1831">     * *REQUIRES* SSL client authentication.</span><a href="#l1831"></a>
<span id="l1832">     *</span><a href="#l1832"></a>
<span id="l1833">     * As long as handshaking has not started, we can change</span><a href="#l1833"></a>
<span id="l1834">     * whether client authentication is needed.  Otherwise,</span><a href="#l1834"></a>
<span id="l1835">     * we will need to wait for the next handshake.</span><a href="#l1835"></a>
<span id="l1836">     */</span><a href="#l1836"></a>
<span id="l1837">    synchronized public void setNeedClientAuth(boolean flag) {</span><a href="#l1837"></a>
<span id="l1838">        doClientAuth = (flag ?</span><a href="#l1838"></a>
<span id="l1839">            SSLEngineImpl.clauth_required : SSLEngineImpl.clauth_none);</span><a href="#l1839"></a>
<span id="l1840"></span><a href="#l1840"></a>
<span id="l1841">        if ((handshaker != null) &amp;&amp;</span><a href="#l1841"></a>
<span id="l1842">                (handshaker instanceof ServerHandshaker) &amp;&amp;</span><a href="#l1842"></a>
<span id="l1843">                !handshaker.activated()) {</span><a href="#l1843"></a>
<span id="l1844">            ((ServerHandshaker) handshaker).setClientAuth(doClientAuth);</span><a href="#l1844"></a>
<span id="l1845">        }</span><a href="#l1845"></a>
<span id="l1846">    }</span><a href="#l1846"></a>
<span id="l1847"></span><a href="#l1847"></a>
<span id="l1848">    synchronized public boolean getNeedClientAuth() {</span><a href="#l1848"></a>
<span id="l1849">        return (doClientAuth == SSLEngineImpl.clauth_required);</span><a href="#l1849"></a>
<span id="l1850">    }</span><a href="#l1850"></a>
<span id="l1851"></span><a href="#l1851"></a>
<span id="l1852">    /**</span><a href="#l1852"></a>
<span id="l1853">     * Sets the flag controlling whether a server mode engine</span><a href="#l1853"></a>
<span id="l1854">     * *REQUESTS* SSL client authentication.</span><a href="#l1854"></a>
<span id="l1855">     *</span><a href="#l1855"></a>
<span id="l1856">     * As long as handshaking has not started, we can change</span><a href="#l1856"></a>
<span id="l1857">     * whether client authentication is requested.  Otherwise,</span><a href="#l1857"></a>
<span id="l1858">     * we will need to wait for the next handshake.</span><a href="#l1858"></a>
<span id="l1859">     */</span><a href="#l1859"></a>
<span id="l1860">    synchronized public void setWantClientAuth(boolean flag) {</span><a href="#l1860"></a>
<span id="l1861">        doClientAuth = (flag ?</span><a href="#l1861"></a>
<span id="l1862">            SSLEngineImpl.clauth_requested : SSLEngineImpl.clauth_none);</span><a href="#l1862"></a>
<span id="l1863"></span><a href="#l1863"></a>
<span id="l1864">        if ((handshaker != null) &amp;&amp;</span><a href="#l1864"></a>
<span id="l1865">                (handshaker instanceof ServerHandshaker) &amp;&amp;</span><a href="#l1865"></a>
<span id="l1866">                !handshaker.activated()) {</span><a href="#l1866"></a>
<span id="l1867">            ((ServerHandshaker) handshaker).setClientAuth(doClientAuth);</span><a href="#l1867"></a>
<span id="l1868">        }</span><a href="#l1868"></a>
<span id="l1869">    }</span><a href="#l1869"></a>
<span id="l1870"></span><a href="#l1870"></a>
<span id="l1871">    synchronized public boolean getWantClientAuth() {</span><a href="#l1871"></a>
<span id="l1872">        return (doClientAuth == SSLEngineImpl.clauth_requested);</span><a href="#l1872"></a>
<span id="l1873">    }</span><a href="#l1873"></a>
<span id="l1874"></span><a href="#l1874"></a>
<span id="l1875"></span><a href="#l1875"></a>
<span id="l1876">    /**</span><a href="#l1876"></a>
<span id="l1877">     * Sets the flag controlling whether the engine is in SSL</span><a href="#l1877"></a>
<span id="l1878">     * client or server mode.  Must be called before any SSL</span><a href="#l1878"></a>
<span id="l1879">     * traffic has started.</span><a href="#l1879"></a>
<span id="l1880">     */</span><a href="#l1880"></a>
<span id="l1881">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l1881"></a>
<span id="l1882">    synchronized public void setUseClientMode(boolean flag) {</span><a href="#l1882"></a>
<span id="l1883">        switch (connectionState) {</span><a href="#l1883"></a>
<span id="l1884"></span><a href="#l1884"></a>
<span id="l1885">        case cs_START:</span><a href="#l1885"></a>
<span id="l1886">            /*</span><a href="#l1886"></a>
<span id="l1887">             * If we need to change the socket mode and the enabled</span><a href="#l1887"></a>
<span id="l1888">             * protocols and cipher suites haven't specifically been</span><a href="#l1888"></a>
<span id="l1889">             * set by the user, change them to the corresponding</span><a href="#l1889"></a>
<span id="l1890">             * default ones.</span><a href="#l1890"></a>
<span id="l1891">             */</span><a href="#l1891"></a>
<span id="l1892">            if (roleIsServer != (!flag)) {</span><a href="#l1892"></a>
<span id="l1893">                if (sslContext.isDefaultProtocolList(enabledProtocols)) {</span><a href="#l1893"></a>
<span id="l1894">                    enabledProtocols =</span><a href="#l1894"></a>
<span id="l1895">                            sslContext.getDefaultProtocolList(!flag);</span><a href="#l1895"></a>
<span id="l1896">                }</span><a href="#l1896"></a>
<span id="l1897"></span><a href="#l1897"></a>
<span id="l1898">                if (sslContext.isDefaultCipherSuiteList(enabledCipherSuites)) {</span><a href="#l1898"></a>
<span id="l1899">                    enabledCipherSuites =</span><a href="#l1899"></a>
<span id="l1900">                            sslContext.getDefaultCipherSuiteList(!flag);</span><a href="#l1900"></a>
<span id="l1901">                }</span><a href="#l1901"></a>
<span id="l1902">            }</span><a href="#l1902"></a>
<span id="l1903"></span><a href="#l1903"></a>
<span id="l1904">            roleIsServer = !flag;</span><a href="#l1904"></a>
<span id="l1905">            serverModeSet = true;</span><a href="#l1905"></a>
<span id="l1906">            break;</span><a href="#l1906"></a>
<span id="l1907"></span><a href="#l1907"></a>
<span id="l1908">        case cs_HANDSHAKE:</span><a href="#l1908"></a>
<span id="l1909">            /*</span><a href="#l1909"></a>
<span id="l1910">             * If we have a handshaker, but haven't started</span><a href="#l1910"></a>
<span id="l1911">             * SSL traffic, we can throw away our current</span><a href="#l1911"></a>
<span id="l1912">             * handshaker, and start from scratch.  Don't</span><a href="#l1912"></a>
<span id="l1913">             * need to call doneConnect() again, we already</span><a href="#l1913"></a>
<span id="l1914">             * have the streams.</span><a href="#l1914"></a>
<span id="l1915">             */</span><a href="#l1915"></a>
<span id="l1916">            assert(handshaker != null);</span><a href="#l1916"></a>
<span id="l1917">            if (!handshaker.activated()) {</span><a href="#l1917"></a>
<span id="l1918">                /*</span><a href="#l1918"></a>
<span id="l1919">                 * If we need to change the engine mode and the enabled</span><a href="#l1919"></a>
<span id="l1920">                 * protocols haven't specifically been set by the user,</span><a href="#l1920"></a>
<span id="l1921">                 * change them to the corresponding default ones.</span><a href="#l1921"></a>
<span id="l1922">                 */</span><a href="#l1922"></a>
<span id="l1923">                if (roleIsServer != (!flag) &amp;&amp;</span><a href="#l1923"></a>
<span id="l1924">                        sslContext.isDefaultProtocolList(enabledProtocols)) {</span><a href="#l1924"></a>
<span id="l1925">                    enabledProtocols = sslContext.getDefaultProtocolList(!flag);</span><a href="#l1925"></a>
<span id="l1926">                }</span><a href="#l1926"></a>
<span id="l1927"></span><a href="#l1927"></a>
<span id="l1928">                roleIsServer = !flag;</span><a href="#l1928"></a>
<span id="l1929">                connectionState = cs_START;</span><a href="#l1929"></a>
<span id="l1930">                initHandshaker();</span><a href="#l1930"></a>
<span id="l1931">                break;</span><a href="#l1931"></a>
<span id="l1932">            }</span><a href="#l1932"></a>
<span id="l1933"></span><a href="#l1933"></a>
<span id="l1934">            // If handshake has started, that's an error.  Fall through...</span><a href="#l1934"></a>
<span id="l1935"></span><a href="#l1935"></a>
<span id="l1936">        default:</span><a href="#l1936"></a>
<span id="l1937">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1937"></a>
<span id="l1938">                System.out.println(threadName() +</span><a href="#l1938"></a>
<span id="l1939">                    &quot;, setUseClientMode() invoked in state = &quot; +</span><a href="#l1939"></a>
<span id="l1940">                    connectionState);</span><a href="#l1940"></a>
<span id="l1941">            }</span><a href="#l1941"></a>
<span id="l1942"></span><a href="#l1942"></a>
<span id="l1943">            /*</span><a href="#l1943"></a>
<span id="l1944">             * We can let them continue if they catch this correctly,</span><a href="#l1944"></a>
<span id="l1945">             * we don't need to shut this down.</span><a href="#l1945"></a>
<span id="l1946">             */</span><a href="#l1946"></a>
<span id="l1947">            throw new IllegalArgumentException(</span><a href="#l1947"></a>
<span id="l1948">                &quot;Cannot change mode after SSL traffic has started&quot;);</span><a href="#l1948"></a>
<span id="l1949">        }</span><a href="#l1949"></a>
<span id="l1950">    }</span><a href="#l1950"></a>
<span id="l1951"></span><a href="#l1951"></a>
<span id="l1952">    synchronized public boolean getUseClientMode() {</span><a href="#l1952"></a>
<span id="l1953">        return !roleIsServer;</span><a href="#l1953"></a>
<span id="l1954">    }</span><a href="#l1954"></a>
<span id="l1955"></span><a href="#l1955"></a>
<span id="l1956"></span><a href="#l1956"></a>
<span id="l1957">    /**</span><a href="#l1957"></a>
<span id="l1958">     * Returns the names of the cipher suites which could be enabled for use</span><a href="#l1958"></a>
<span id="l1959">     * on an SSL connection.  Normally, only a subset of these will actually</span><a href="#l1959"></a>
<span id="l1960">     * be enabled by default, since this list may include cipher suites which</span><a href="#l1960"></a>
<span id="l1961">     * do not support the mutual authentication of servers and clients, or</span><a href="#l1961"></a>
<span id="l1962">     * which do not protect data confidentiality.  Servers may also need</span><a href="#l1962"></a>
<span id="l1963">     * certain kinds of certificates to use certain cipher suites.</span><a href="#l1963"></a>
<span id="l1964">     *</span><a href="#l1964"></a>
<span id="l1965">     * @return an array of cipher suite names</span><a href="#l1965"></a>
<span id="l1966">     */</span><a href="#l1966"></a>
<span id="l1967">    public String[] getSupportedCipherSuites() {</span><a href="#l1967"></a>
<span id="l1968">        return sslContext.getSupportedCipherSuiteList().toStringArray();</span><a href="#l1968"></a>
<span id="l1969">    }</span><a href="#l1969"></a>
<span id="l1970"></span><a href="#l1970"></a>
<span id="l1971">    /**</span><a href="#l1971"></a>
<span id="l1972">     * Controls which particular cipher suites are enabled for use on</span><a href="#l1972"></a>
<span id="l1973">     * this connection.  The cipher suites must have been listed by</span><a href="#l1973"></a>
<span id="l1974">     * getCipherSuites() as being supported.  Even if a suite has been</span><a href="#l1974"></a>
<span id="l1975">     * enabled, it might never be used if no peer supports it or the</span><a href="#l1975"></a>
<span id="l1976">     * requisite certificates (and private keys) are not available.</span><a href="#l1976"></a>
<span id="l1977">     *</span><a href="#l1977"></a>
<span id="l1978">     * @param suites Names of all the cipher suites to enable.</span><a href="#l1978"></a>
<span id="l1979">     */</span><a href="#l1979"></a>
<span id="l1980">    synchronized public void setEnabledCipherSuites(String[] suites) {</span><a href="#l1980"></a>
<span id="l1981">        enabledCipherSuites = new CipherSuiteList(suites);</span><a href="#l1981"></a>
<span id="l1982">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l1982"></a>
<span id="l1983">            handshaker.setEnabledCipherSuites(enabledCipherSuites);</span><a href="#l1983"></a>
<span id="l1984">        }</span><a href="#l1984"></a>
<span id="l1985">    }</span><a href="#l1985"></a>
<span id="l1986"></span><a href="#l1986"></a>
<span id="l1987">    /**</span><a href="#l1987"></a>
<span id="l1988">     * Returns the names of the SSL cipher suites which are currently enabled</span><a href="#l1988"></a>
<span id="l1989">     * for use on this connection.  When an SSL engine is first created,</span><a href="#l1989"></a>
<span id="l1990">     * all enabled cipher suites &lt;em&gt;(a)&lt;/em&gt; protect data confidentiality,</span><a href="#l1990"></a>
<span id="l1991">     * by traffic encryption, and &lt;em&gt;(b)&lt;/em&gt; can mutually authenticate</span><a href="#l1991"></a>
<span id="l1992">     * both clients and servers.  Thus, in some environments, this value</span><a href="#l1992"></a>
<span id="l1993">     * might be empty.</span><a href="#l1993"></a>
<span id="l1994">     *</span><a href="#l1994"></a>
<span id="l1995">     * @return an array of cipher suite names</span><a href="#l1995"></a>
<span id="l1996">     */</span><a href="#l1996"></a>
<span id="l1997">    synchronized public String[] getEnabledCipherSuites() {</span><a href="#l1997"></a>
<span id="l1998">        return enabledCipherSuites.toStringArray();</span><a href="#l1998"></a>
<span id="l1999">    }</span><a href="#l1999"></a>
<span id="l2000"></span><a href="#l2000"></a>
<span id="l2001"></span><a href="#l2001"></a>
<span id="l2002">    /**</span><a href="#l2002"></a>
<span id="l2003">     * Returns the protocols that are supported by this implementation.</span><a href="#l2003"></a>
<span id="l2004">     * A subset of the supported protocols may be enabled for this connection</span><a href="#l2004"></a>
<span id="l2005">     * @return an array of protocol names.</span><a href="#l2005"></a>
<span id="l2006">     */</span><a href="#l2006"></a>
<span id="l2007">    public String[] getSupportedProtocols() {</span><a href="#l2007"></a>
<span id="l2008">        return sslContext.getSuportedProtocolList().toStringArray();</span><a href="#l2008"></a>
<span id="l2009">    }</span><a href="#l2009"></a>
<span id="l2010"></span><a href="#l2010"></a>
<span id="l2011">    /**</span><a href="#l2011"></a>
<span id="l2012">     * Controls which protocols are enabled for use on</span><a href="#l2012"></a>
<span id="l2013">     * this connection.  The protocols must have been listed by</span><a href="#l2013"></a>
<span id="l2014">     * getSupportedProtocols() as being supported.</span><a href="#l2014"></a>
<span id="l2015">     *</span><a href="#l2015"></a>
<span id="l2016">     * @param protocols protocols to enable.</span><a href="#l2016"></a>
<span id="l2017">     * @exception IllegalArgumentException when one of the protocols</span><a href="#l2017"></a>
<span id="l2018">     *  named by the parameter is not supported.</span><a href="#l2018"></a>
<span id="l2019">     */</span><a href="#l2019"></a>
<span id="l2020">    synchronized public void setEnabledProtocols(String[] protocols) {</span><a href="#l2020"></a>
<span id="l2021">        enabledProtocols = new ProtocolList(protocols);</span><a href="#l2021"></a>
<span id="l2022">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l2022"></a>
<span id="l2023">            handshaker.setEnabledProtocols(enabledProtocols);</span><a href="#l2023"></a>
<span id="l2024">        }</span><a href="#l2024"></a>
<span id="l2025">    }</span><a href="#l2025"></a>
<span id="l2026"></span><a href="#l2026"></a>
<span id="l2027">    synchronized public String[] getEnabledProtocols() {</span><a href="#l2027"></a>
<span id="l2028">        return enabledProtocols.toStringArray();</span><a href="#l2028"></a>
<span id="l2029">    }</span><a href="#l2029"></a>
<span id="l2030"></span><a href="#l2030"></a>
<span id="l2031">    /**</span><a href="#l2031"></a>
<span id="l2032">     * Returns the SSLParameters in effect for this SSLEngine.</span><a href="#l2032"></a>
<span id="l2033">     */</span><a href="#l2033"></a>
<span id="l2034">    synchronized public SSLParameters getSSLParameters() {</span><a href="#l2034"></a>
<span id="l2035">        SSLParameters params = super.getSSLParameters();</span><a href="#l2035"></a>
<span id="l2036"></span><a href="#l2036"></a>
<span id="l2037">        // the super implementation does not handle the following parameters</span><a href="#l2037"></a>
<span id="l2038">        params.setEndpointIdentificationAlgorithm(identificationProtocol);</span><a href="#l2038"></a>
<span id="l2039">        params.setAlgorithmConstraints(algorithmConstraints);</span><a href="#l2039"></a>
<span id="l2040"></span><a href="#l2040"></a>
<span id="l2041">        return params;</span><a href="#l2041"></a>
<span id="l2042">    }</span><a href="#l2042"></a>
<span id="l2043"></span><a href="#l2043"></a>
<span id="l2044">    /**</span><a href="#l2044"></a>
<span id="l2045">     * Applies SSLParameters to this engine.</span><a href="#l2045"></a>
<span id="l2046">     */</span><a href="#l2046"></a>
<span id="l2047">    synchronized public void setSSLParameters(SSLParameters params) {</span><a href="#l2047"></a>
<span id="l2048">        super.setSSLParameters(params);</span><a href="#l2048"></a>
<span id="l2049"></span><a href="#l2049"></a>
<span id="l2050">        // the super implementation does not handle the following parameters</span><a href="#l2050"></a>
<span id="l2051">        identificationProtocol = params.getEndpointIdentificationAlgorithm();</span><a href="#l2051"></a>
<span id="l2052">        algorithmConstraints = params.getAlgorithmConstraints();</span><a href="#l2052"></a>
<span id="l2053">        if ((handshaker != null) &amp;&amp; !handshaker.started()) {</span><a href="#l2053"></a>
<span id="l2054">            handshaker.setIdentificationProtocol(identificationProtocol);</span><a href="#l2054"></a>
<span id="l2055">            handshaker.setAlgorithmConstraints(algorithmConstraints);</span><a href="#l2055"></a>
<span id="l2056">        }</span><a href="#l2056"></a>
<span id="l2057">    }</span><a href="#l2057"></a>
<span id="l2058"></span><a href="#l2058"></a>
<span id="l2059">    /**</span><a href="#l2059"></a>
<span id="l2060">     * Return the name of the current thread. Utility method.</span><a href="#l2060"></a>
<span id="l2061">     */</span><a href="#l2061"></a>
<span id="l2062">    private static String threadName() {</span><a href="#l2062"></a>
<span id="l2063">        return Thread.currentThread().getName();</span><a href="#l2063"></a>
<span id="l2064">    }</span><a href="#l2064"></a>
<span id="l2065"></span><a href="#l2065"></a>
<span id="l2066">    /**</span><a href="#l2066"></a>
<span id="l2067">     * Returns a printable representation of this end of the connection.</span><a href="#l2067"></a>
<span id="l2068">     */</span><a href="#l2068"></a>
<span id="l2069">    public String toString() {</span><a href="#l2069"></a>
<span id="l2070">        StringBuilder retval = new StringBuilder(80);</span><a href="#l2070"></a>
<span id="l2071"></span><a href="#l2071"></a>
<span id="l2072">        retval.append(Integer.toHexString(hashCode()));</span><a href="#l2072"></a>
<span id="l2073">        retval.append(&quot;[&quot;);</span><a href="#l2073"></a>
<span id="l2074">        retval.append(&quot;SSLEngine[hostname=&quot;);</span><a href="#l2074"></a>
<span id="l2075">        String host = getPeerHost();</span><a href="#l2075"></a>
<span id="l2076">        retval.append((host == null) ? &quot;null&quot; : host);</span><a href="#l2076"></a>
<span id="l2077">        retval.append(&quot; port=&quot;);</span><a href="#l2077"></a>
<span id="l2078">        retval.append(Integer.toString(getPeerPort()));</span><a href="#l2078"></a>
<span id="l2079">        retval.append(&quot;] &quot;);</span><a href="#l2079"></a>
<span id="l2080">        retval.append(getSession().getCipherSuite());</span><a href="#l2080"></a>
<span id="l2081">        retval.append(&quot;]&quot;);</span><a href="#l2081"></a>
<span id="l2082"></span><a href="#l2082"></a>
<span id="l2083">        return retval.toString();</span><a href="#l2083"></a>
<span id="l2084">    }</span><a href="#l2084"></a>
<span id="l2085">}</span><a href="#l2085"></a></pre>
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

