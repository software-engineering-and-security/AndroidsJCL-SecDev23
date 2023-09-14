<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/sun/nio/ch/SocketAdaptor.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/c9b0a18f082e">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/c9b0a18f082e">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/c9b0a18f082e">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/c9b0a18f082e/src/share/classes/sun/nio/ch/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/nio/ch/SocketAdaptor.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketAdaptor.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketAdaptor.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketAdaptor.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketAdaptor.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketAdaptor.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/nio/ch/SocketAdaptor.java @ 8936:c9b0a18f082e</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8196956: (ch) More channels cleanup
8231795: Enhance datagram socket support
Reviewed-by: rriggs, prappo, bpb</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#97;&#110;&#98;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 22 Jan 2020 06:51:23 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/ff8c7bdc6405/src/share/classes/sun/nio/ch/SocketAdaptor.java">ff8c7bdc6405</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.nio.ch;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.InputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.OutputStream;</span><a href="#l30"></a>
<span id="l31">import java.net.InetAddress;</span><a href="#l31"></a>
<span id="l32">import java.net.InetSocketAddress;</span><a href="#l32"></a>
<span id="l33">import java.net.Socket;</span><a href="#l33"></a>
<span id="l34">import java.net.SocketAddress;</span><a href="#l34"></a>
<span id="l35">import java.net.SocketException;</span><a href="#l35"></a>
<span id="l36">import java.net.SocketImpl;</span><a href="#l36"></a>
<span id="l37">import java.net.SocketOption;</span><a href="#l37"></a>
<span id="l38">import java.net.SocketTimeoutException;</span><a href="#l38"></a>
<span id="l39">import java.net.StandardSocketOptions;</span><a href="#l39"></a>
<span id="l40">import java.nio.ByteBuffer;</span><a href="#l40"></a>
<span id="l41">import java.nio.channels.Channels;</span><a href="#l41"></a>
<span id="l42">import java.nio.channels.ClosedChannelException;</span><a href="#l42"></a>
<span id="l43">import java.nio.channels.IllegalBlockingModeException;</span><a href="#l43"></a>
<span id="l44">import java.nio.channels.SocketChannel;</span><a href="#l44"></a>
<span id="l45">import java.security.AccessController;</span><a href="#l45"></a>
<span id="l46">import java.security.PrivilegedExceptionAction;</span><a href="#l46"></a>
<span id="l47">import java.util.*;</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">import sun.misc.IoTrace;</span><a href="#l49"></a>
<span id="l50"></span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">// Make a socket channel look like a socket.</span><a href="#l52"></a>
<span id="l53">//</span><a href="#l53"></a>
<span id="l54">// The only aspects of java.net.Socket-hood that we don't attempt to emulate</span><a href="#l54"></a>
<span id="l55">// here are the interrupted-I/O exceptions (which our Solaris implementations</span><a href="#l55"></a>
<span id="l56">// attempt to support) and the sending of urgent data.  Otherwise an adapted</span><a href="#l56"></a>
<span id="l57">// socket should look enough like a real java.net.Socket to fool most of the</span><a href="#l57"></a>
<span id="l58">// developers most of the time, right down to the exception message strings.</span><a href="#l58"></a>
<span id="l59">//</span><a href="#l59"></a>
<span id="l60">// The methods in this class are defined in exactly the same order as in</span><a href="#l60"></a>
<span id="l61">// java.net.Socket so as to simplify tracking future changes to that class.</span><a href="#l61"></a>
<span id="l62">//</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">class SocketAdaptor</span><a href="#l64"></a>
<span id="l65">    extends Socket</span><a href="#l65"></a>
<span id="l66">{</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    // The channel being adapted</span><a href="#l68"></a>
<span id="l69">    private final SocketChannelImpl sc;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    // Timeout &quot;option&quot; value for reads</span><a href="#l71"></a>
<span id="l72">    private volatile int timeout = 0;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    private SocketAdaptor(SocketChannelImpl sc) throws SocketException {</span><a href="#l74"></a>
<span id="l75">        super((SocketImpl) null);</span><a href="#l75"></a>
<span id="l76">        this.sc = sc;</span><a href="#l76"></a>
<span id="l77">    }</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    public static Socket create(SocketChannelImpl sc) {</span><a href="#l79"></a>
<span id="l80">        try {</span><a href="#l80"></a>
<span id="l81">            return new SocketAdaptor(sc);</span><a href="#l81"></a>
<span id="l82">        } catch (SocketException e) {</span><a href="#l82"></a>
<span id="l83">            throw new InternalError(&quot;Should not reach here&quot;);</span><a href="#l83"></a>
<span id="l84">        }</span><a href="#l84"></a>
<span id="l85">    }</span><a href="#l85"></a>
<span id="l86"></span><a href="#l86"></a>
<span id="l87">    public SocketChannel getChannel() {</span><a href="#l87"></a>
<span id="l88">        return sc;</span><a href="#l88"></a>
<span id="l89">    }</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">    // Override this method just to protect against changes in the superclass</span><a href="#l91"></a>
<span id="l92">    //</span><a href="#l92"></a>
<span id="l93">    public void connect(SocketAddress remote) throws IOException {</span><a href="#l93"></a>
<span id="l94">        connect(remote, 0);</span><a href="#l94"></a>
<span id="l95">    }</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    public void connect(SocketAddress remote, int timeout) throws IOException {</span><a href="#l97"></a>
<span id="l98">        if (remote == null)</span><a href="#l98"></a>
<span id="l99">            throw new IllegalArgumentException(&quot;connect: The address can't be null&quot;);</span><a href="#l99"></a>
<span id="l100">        if (timeout &lt; 0)</span><a href="#l100"></a>
<span id="l101">            throw new IllegalArgumentException(&quot;connect: timeout can't be negative&quot;);</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">        synchronized (sc.blockingLock()) {</span><a href="#l103"></a>
<span id="l104">            if (!sc.isBlocking())</span><a href="#l104"></a>
<span id="l105">                throw new IllegalBlockingModeException();</span><a href="#l105"></a>
<span id="l106"></span><a href="#l106"></a>
<span id="l107">            try {</span><a href="#l107"></a>
<span id="l108">                if (timeout == 0) {</span><a href="#l108"></a>
<span id="l109">                    sc.connect(remote);</span><a href="#l109"></a>
<span id="l110">                    return;</span><a href="#l110"></a>
<span id="l111">                }</span><a href="#l111"></a>
<span id="l112"></span><a href="#l112"></a>
<span id="l113">                sc.configureBlocking(false);</span><a href="#l113"></a>
<span id="l114">                try {</span><a href="#l114"></a>
<span id="l115">                    if (sc.connect(remote))</span><a href="#l115"></a>
<span id="l116">                        return;</span><a href="#l116"></a>
<span id="l117">                    long to = timeout;</span><a href="#l117"></a>
<span id="l118">                    for (;;) {</span><a href="#l118"></a>
<span id="l119">                        if (!sc.isOpen())</span><a href="#l119"></a>
<span id="l120">                            throw new ClosedChannelException();</span><a href="#l120"></a>
<span id="l121">                        long st = System.currentTimeMillis();</span><a href="#l121"></a>
<span id="l122"></span><a href="#l122"></a>
<span id="l123">                        int result = sc.poll(Net.POLLCONN, to);</span><a href="#l123"></a>
<span id="l124">                        if (result &gt; 0 &amp;&amp; sc.finishConnect())</span><a href="#l124"></a>
<span id="l125">                            break;</span><a href="#l125"></a>
<span id="l126">                        to -= System.currentTimeMillis() - st;</span><a href="#l126"></a>
<span id="l127">                        if (to &lt;= 0) {</span><a href="#l127"></a>
<span id="l128">                            try {</span><a href="#l128"></a>
<span id="l129">                                sc.close();</span><a href="#l129"></a>
<span id="l130">                            } catch (IOException x) { }</span><a href="#l130"></a>
<span id="l131">                            throw new SocketTimeoutException();</span><a href="#l131"></a>
<span id="l132">                        }</span><a href="#l132"></a>
<span id="l133">                    }</span><a href="#l133"></a>
<span id="l134">                } finally {</span><a href="#l134"></a>
<span id="l135">                    try {</span><a href="#l135"></a>
<span id="l136">                        sc.configureBlocking(true);</span><a href="#l136"></a>
<span id="l137">                    } catch (ClosedChannelException e) { }</span><a href="#l137"></a>
<span id="l138">                }</span><a href="#l138"></a>
<span id="l139"></span><a href="#l139"></a>
<span id="l140">            } catch (Exception x) {</span><a href="#l140"></a>
<span id="l141">                Net.translateException(x, true);</span><a href="#l141"></a>
<span id="l142">            }</span><a href="#l142"></a>
<span id="l143">        }</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">    }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">    public void bind(SocketAddress local) throws IOException {</span><a href="#l147"></a>
<span id="l148">        try {</span><a href="#l148"></a>
<span id="l149">            sc.bind(local);</span><a href="#l149"></a>
<span id="l150">        } catch (Exception x) {</span><a href="#l150"></a>
<span id="l151">            Net.translateException(x);</span><a href="#l151"></a>
<span id="l152">        }</span><a href="#l152"></a>
<span id="l153">    }</span><a href="#l153"></a>
<span id="l154"></span><a href="#l154"></a>
<span id="l155">    public InetAddress getInetAddress() {</span><a href="#l155"></a>
<span id="l156">        SocketAddress remote = sc.remoteAddress();</span><a href="#l156"></a>
<span id="l157">        if (remote == null) {</span><a href="#l157"></a>
<span id="l158">            return null;</span><a href="#l158"></a>
<span id="l159">        } else {</span><a href="#l159"></a>
<span id="l160">            return ((InetSocketAddress)remote).getAddress();</span><a href="#l160"></a>
<span id="l161">        }</span><a href="#l161"></a>
<span id="l162">    }</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">    public InetAddress getLocalAddress() {</span><a href="#l164"></a>
<span id="l165">        if (sc.isOpen()) {</span><a href="#l165"></a>
<span id="l166">            InetSocketAddress local = sc.localAddress();</span><a href="#l166"></a>
<span id="l167">            if (local != null)</span><a href="#l167"></a>
<span id="l168">                return Net.getRevealedLocalAddress(local).getAddress();</span><a href="#l168"></a>
<span id="l169">        }</span><a href="#l169"></a>
<span id="l170">        return new InetSocketAddress(0).getAddress();</span><a href="#l170"></a>
<span id="l171">    }</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">    public int getPort() {</span><a href="#l173"></a>
<span id="l174">        SocketAddress remote = sc.remoteAddress();</span><a href="#l174"></a>
<span id="l175">        if (remote == null) {</span><a href="#l175"></a>
<span id="l176">            return 0;</span><a href="#l176"></a>
<span id="l177">        } else {</span><a href="#l177"></a>
<span id="l178">            return ((InetSocketAddress)remote).getPort();</span><a href="#l178"></a>
<span id="l179">        }</span><a href="#l179"></a>
<span id="l180">    }</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    public int getLocalPort() {</span><a href="#l182"></a>
<span id="l183">        SocketAddress local = sc.localAddress();</span><a href="#l183"></a>
<span id="l184">        if (local == null) {</span><a href="#l184"></a>
<span id="l185">            return -1;</span><a href="#l185"></a>
<span id="l186">        } else {</span><a href="#l186"></a>
<span id="l187">            return ((InetSocketAddress)local).getPort();</span><a href="#l187"></a>
<span id="l188">        }</span><a href="#l188"></a>
<span id="l189">    }</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    private class SocketInputStream</span><a href="#l191"></a>
<span id="l192">        extends ChannelInputStream</span><a href="#l192"></a>
<span id="l193">    {</span><a href="#l193"></a>
<span id="l194">        private SocketInputStream() {</span><a href="#l194"></a>
<span id="l195">            super(sc);</span><a href="#l195"></a>
<span id="l196">        }</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">        protected int read(ByteBuffer bb)</span><a href="#l198"></a>
<span id="l199">            throws IOException</span><a href="#l199"></a>
<span id="l200">        {</span><a href="#l200"></a>
<span id="l201">            synchronized (sc.blockingLock()) {</span><a href="#l201"></a>
<span id="l202">                if (!sc.isBlocking())</span><a href="#l202"></a>
<span id="l203">                    throw new IllegalBlockingModeException();</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">                if (timeout == 0)</span><a href="#l205"></a>
<span id="l206">                    return sc.read(bb);</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">                sc.configureBlocking(false);</span><a href="#l208"></a>
<span id="l209">                int n = 0;</span><a href="#l209"></a>
<span id="l210">                Object traceContext = IoTrace.socketReadBegin();</span><a href="#l210"></a>
<span id="l211">                try {</span><a href="#l211"></a>
<span id="l212">                    if ((n = sc.read(bb)) != 0)</span><a href="#l212"></a>
<span id="l213">                        return n;</span><a href="#l213"></a>
<span id="l214">                    long to = timeout;</span><a href="#l214"></a>
<span id="l215">                    for (;;) {</span><a href="#l215"></a>
<span id="l216">                        if (!sc.isOpen())</span><a href="#l216"></a>
<span id="l217">                            throw new ClosedChannelException();</span><a href="#l217"></a>
<span id="l218">                        long st = System.currentTimeMillis();</span><a href="#l218"></a>
<span id="l219">                        int result = sc.poll(Net.POLLIN, to);</span><a href="#l219"></a>
<span id="l220">                        if (result &gt; 0) {</span><a href="#l220"></a>
<span id="l221">                            if ((n = sc.read(bb)) != 0)</span><a href="#l221"></a>
<span id="l222">                                return n;</span><a href="#l222"></a>
<span id="l223">                        }</span><a href="#l223"></a>
<span id="l224">                        to -= System.currentTimeMillis() - st;</span><a href="#l224"></a>
<span id="l225">                        if (to &lt;= 0)</span><a href="#l225"></a>
<span id="l226">                            throw new SocketTimeoutException();</span><a href="#l226"></a>
<span id="l227">                    }</span><a href="#l227"></a>
<span id="l228">                } finally {</span><a href="#l228"></a>
<span id="l229">                    IoTrace.socketReadEnd(traceContext, getInetAddress(),</span><a href="#l229"></a>
<span id="l230">                                          getPort(), timeout, n &gt; 0 ? n : 0);</span><a href="#l230"></a>
<span id="l231">                    try {</span><a href="#l231"></a>
<span id="l232">                        sc.configureBlocking(true);</span><a href="#l232"></a>
<span id="l233">                    } catch (ClosedChannelException e) { }</span><a href="#l233"></a>
<span id="l234">                }</span><a href="#l234"></a>
<span id="l235">            }</span><a href="#l235"></a>
<span id="l236">        }</span><a href="#l236"></a>
<span id="l237">    }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    private InputStream socketInputStream = null;</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">    public InputStream getInputStream() throws IOException {</span><a href="#l241"></a>
<span id="l242">        if (!sc.isOpen())</span><a href="#l242"></a>
<span id="l243">            throw new SocketException(&quot;Socket is closed&quot;);</span><a href="#l243"></a>
<span id="l244">        if (!sc.isConnected())</span><a href="#l244"></a>
<span id="l245">            throw new SocketException(&quot;Socket is not connected&quot;);</span><a href="#l245"></a>
<span id="l246">        if (!sc.isInputOpen())</span><a href="#l246"></a>
<span id="l247">            throw new SocketException(&quot;Socket input is shutdown&quot;);</span><a href="#l247"></a>
<span id="l248">        if (socketInputStream == null) {</span><a href="#l248"></a>
<span id="l249">            try {</span><a href="#l249"></a>
<span id="l250">                socketInputStream = AccessController.doPrivileged(</span><a href="#l250"></a>
<span id="l251">                    new PrivilegedExceptionAction&lt;InputStream&gt;() {</span><a href="#l251"></a>
<span id="l252">                        public InputStream run() throws IOException {</span><a href="#l252"></a>
<span id="l253">                            return new SocketInputStream();</span><a href="#l253"></a>
<span id="l254">                        }</span><a href="#l254"></a>
<span id="l255">                    });</span><a href="#l255"></a>
<span id="l256">            } catch (java.security.PrivilegedActionException e) {</span><a href="#l256"></a>
<span id="l257">                throw (IOException)e.getException();</span><a href="#l257"></a>
<span id="l258">            }</span><a href="#l258"></a>
<span id="l259">        }</span><a href="#l259"></a>
<span id="l260">        return socketInputStream;</span><a href="#l260"></a>
<span id="l261">    }</span><a href="#l261"></a>
<span id="l262"></span><a href="#l262"></a>
<span id="l263">    public OutputStream getOutputStream() throws IOException {</span><a href="#l263"></a>
<span id="l264">        if (!sc.isOpen())</span><a href="#l264"></a>
<span id="l265">            throw new SocketException(&quot;Socket is closed&quot;);</span><a href="#l265"></a>
<span id="l266">        if (!sc.isConnected())</span><a href="#l266"></a>
<span id="l267">            throw new SocketException(&quot;Socket is not connected&quot;);</span><a href="#l267"></a>
<span id="l268">        if (!sc.isOutputOpen())</span><a href="#l268"></a>
<span id="l269">            throw new SocketException(&quot;Socket output is shutdown&quot;);</span><a href="#l269"></a>
<span id="l270">        OutputStream os = null;</span><a href="#l270"></a>
<span id="l271">        try {</span><a href="#l271"></a>
<span id="l272">            os = AccessController.doPrivileged(</span><a href="#l272"></a>
<span id="l273">                new PrivilegedExceptionAction&lt;OutputStream&gt;() {</span><a href="#l273"></a>
<span id="l274">                    public OutputStream run() throws IOException {</span><a href="#l274"></a>
<span id="l275">                        return Channels.newOutputStream(sc);</span><a href="#l275"></a>
<span id="l276">                    }</span><a href="#l276"></a>
<span id="l277">                });</span><a href="#l277"></a>
<span id="l278">        } catch (java.security.PrivilegedActionException e) {</span><a href="#l278"></a>
<span id="l279">            throw (IOException)e.getException();</span><a href="#l279"></a>
<span id="l280">        }</span><a href="#l280"></a>
<span id="l281">        return os;</span><a href="#l281"></a>
<span id="l282">    }</span><a href="#l282"></a>
<span id="l283"></span><a href="#l283"></a>
<span id="l284">    private void setBooleanOption(SocketOption&lt;Boolean&gt; name, boolean value)</span><a href="#l284"></a>
<span id="l285">        throws SocketException</span><a href="#l285"></a>
<span id="l286">    {</span><a href="#l286"></a>
<span id="l287">        try {</span><a href="#l287"></a>
<span id="l288">            sc.setOption(name, value);</span><a href="#l288"></a>
<span id="l289">        } catch (IOException x) {</span><a href="#l289"></a>
<span id="l290">            Net.translateToSocketException(x);</span><a href="#l290"></a>
<span id="l291">        }</span><a href="#l291"></a>
<span id="l292">    }</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">    private void setIntOption(SocketOption&lt;Integer&gt; name, int value)</span><a href="#l294"></a>
<span id="l295">        throws SocketException</span><a href="#l295"></a>
<span id="l296">    {</span><a href="#l296"></a>
<span id="l297">        try {</span><a href="#l297"></a>
<span id="l298">            sc.setOption(name, value);</span><a href="#l298"></a>
<span id="l299">        } catch (IOException x) {</span><a href="#l299"></a>
<span id="l300">            Net.translateToSocketException(x);</span><a href="#l300"></a>
<span id="l301">        }</span><a href="#l301"></a>
<span id="l302">    }</span><a href="#l302"></a>
<span id="l303"></span><a href="#l303"></a>
<span id="l304">    private boolean getBooleanOption(SocketOption&lt;Boolean&gt; name) throws SocketException {</span><a href="#l304"></a>
<span id="l305">        try {</span><a href="#l305"></a>
<span id="l306">            return sc.getOption(name).booleanValue();</span><a href="#l306"></a>
<span id="l307">        } catch (IOException x) {</span><a href="#l307"></a>
<span id="l308">            Net.translateToSocketException(x);</span><a href="#l308"></a>
<span id="l309">            return false;       // keep compiler happy</span><a href="#l309"></a>
<span id="l310">        }</span><a href="#l310"></a>
<span id="l311">    }</span><a href="#l311"></a>
<span id="l312"></span><a href="#l312"></a>
<span id="l313">    private int getIntOption(SocketOption&lt;Integer&gt; name) throws SocketException {</span><a href="#l313"></a>
<span id="l314">        try {</span><a href="#l314"></a>
<span id="l315">            return sc.getOption(name).intValue();</span><a href="#l315"></a>
<span id="l316">        } catch (IOException x) {</span><a href="#l316"></a>
<span id="l317">            Net.translateToSocketException(x);</span><a href="#l317"></a>
<span id="l318">            return -1;          // keep compiler happy</span><a href="#l318"></a>
<span id="l319">        }</span><a href="#l319"></a>
<span id="l320">    }</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">    public void setTcpNoDelay(boolean on) throws SocketException {</span><a href="#l322"></a>
<span id="l323">        setBooleanOption(StandardSocketOptions.TCP_NODELAY, on);</span><a href="#l323"></a>
<span id="l324">    }</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">    public boolean getTcpNoDelay() throws SocketException {</span><a href="#l326"></a>
<span id="l327">        return getBooleanOption(StandardSocketOptions.TCP_NODELAY);</span><a href="#l327"></a>
<span id="l328">    }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">    public void setSoLinger(boolean on, int linger) throws SocketException {</span><a href="#l330"></a>
<span id="l331">        if (!on)</span><a href="#l331"></a>
<span id="l332">            linger = -1;</span><a href="#l332"></a>
<span id="l333">        setIntOption(StandardSocketOptions.SO_LINGER, linger);</span><a href="#l333"></a>
<span id="l334">    }</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">    public int getSoLinger() throws SocketException {</span><a href="#l336"></a>
<span id="l337">        return getIntOption(StandardSocketOptions.SO_LINGER);</span><a href="#l337"></a>
<span id="l338">    }</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">    public void sendUrgentData(int data) throws IOException {</span><a href="#l340"></a>
<span id="l341">        synchronized (sc.blockingLock()) {</span><a href="#l341"></a>
<span id="l342">            if (!sc.isBlocking())</span><a href="#l342"></a>
<span id="l343">                throw new IllegalBlockingModeException();</span><a href="#l343"></a>
<span id="l344">            int n = sc.sendOutOfBandData((byte)data);</span><a href="#l344"></a>
<span id="l345">            assert n == 1;</span><a href="#l345"></a>
<span id="l346">        }</span><a href="#l346"></a>
<span id="l347">    }</span><a href="#l347"></a>
<span id="l348"></span><a href="#l348"></a>
<span id="l349">    public void setOOBInline(boolean on) throws SocketException {</span><a href="#l349"></a>
<span id="l350">        setBooleanOption(ExtendedSocketOption.SO_OOBINLINE, on);</span><a href="#l350"></a>
<span id="l351">    }</span><a href="#l351"></a>
<span id="l352"></span><a href="#l352"></a>
<span id="l353">    public boolean getOOBInline() throws SocketException {</span><a href="#l353"></a>
<span id="l354">        return getBooleanOption(ExtendedSocketOption.SO_OOBINLINE);</span><a href="#l354"></a>
<span id="l355">    }</span><a href="#l355"></a>
<span id="l356"></span><a href="#l356"></a>
<span id="l357">    public void setSoTimeout(int timeout) throws SocketException {</span><a href="#l357"></a>
<span id="l358">        if (timeout &lt; 0)</span><a href="#l358"></a>
<span id="l359">            throw new IllegalArgumentException(&quot;timeout can't be negative&quot;);</span><a href="#l359"></a>
<span id="l360">        this.timeout = timeout;</span><a href="#l360"></a>
<span id="l361">    }</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">    public int getSoTimeout() throws SocketException {</span><a href="#l363"></a>
<span id="l364">        return timeout;</span><a href="#l364"></a>
<span id="l365">    }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">    public void setSendBufferSize(int size) throws SocketException {</span><a href="#l367"></a>
<span id="l368">        // size 0 valid for SocketChannel, invalid for Socket</span><a href="#l368"></a>
<span id="l369">        if (size &lt;= 0)</span><a href="#l369"></a>
<span id="l370">            throw new IllegalArgumentException(&quot;Invalid send size&quot;);</span><a href="#l370"></a>
<span id="l371">        setIntOption(StandardSocketOptions.SO_SNDBUF, size);</span><a href="#l371"></a>
<span id="l372">    }</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">    public int getSendBufferSize() throws SocketException {</span><a href="#l374"></a>
<span id="l375">        return getIntOption(StandardSocketOptions.SO_SNDBUF);</span><a href="#l375"></a>
<span id="l376">    }</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">    public void setReceiveBufferSize(int size) throws SocketException {</span><a href="#l378"></a>
<span id="l379">        // size 0 valid for SocketChannel, invalid for Socket</span><a href="#l379"></a>
<span id="l380">        if (size &lt;= 0)</span><a href="#l380"></a>
<span id="l381">            throw new IllegalArgumentException(&quot;Invalid receive size&quot;);</span><a href="#l381"></a>
<span id="l382">        setIntOption(StandardSocketOptions.SO_RCVBUF, size);</span><a href="#l382"></a>
<span id="l383">    }</span><a href="#l383"></a>
<span id="l384"></span><a href="#l384"></a>
<span id="l385">    public int getReceiveBufferSize() throws SocketException {</span><a href="#l385"></a>
<span id="l386">        return getIntOption(StandardSocketOptions.SO_RCVBUF);</span><a href="#l386"></a>
<span id="l387">    }</span><a href="#l387"></a>
<span id="l388"></span><a href="#l388"></a>
<span id="l389">    public void setKeepAlive(boolean on) throws SocketException {</span><a href="#l389"></a>
<span id="l390">        setBooleanOption(StandardSocketOptions.SO_KEEPALIVE, on);</span><a href="#l390"></a>
<span id="l391">    }</span><a href="#l391"></a>
<span id="l392"></span><a href="#l392"></a>
<span id="l393">    public boolean getKeepAlive() throws SocketException {</span><a href="#l393"></a>
<span id="l394">        return getBooleanOption(StandardSocketOptions.SO_KEEPALIVE);</span><a href="#l394"></a>
<span id="l395">    }</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">    public void setTrafficClass(int tc) throws SocketException {</span><a href="#l397"></a>
<span id="l398">        setIntOption(StandardSocketOptions.IP_TOS, tc);</span><a href="#l398"></a>
<span id="l399">    }</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">    public int getTrafficClass() throws SocketException {</span><a href="#l401"></a>
<span id="l402">        return getIntOption(StandardSocketOptions.IP_TOS);</span><a href="#l402"></a>
<span id="l403">    }</span><a href="#l403"></a>
<span id="l404"></span><a href="#l404"></a>
<span id="l405">    public void setReuseAddress(boolean on) throws SocketException {</span><a href="#l405"></a>
<span id="l406">        setBooleanOption(StandardSocketOptions.SO_REUSEADDR, on);</span><a href="#l406"></a>
<span id="l407">    }</span><a href="#l407"></a>
<span id="l408"></span><a href="#l408"></a>
<span id="l409">    public boolean getReuseAddress() throws SocketException {</span><a href="#l409"></a>
<span id="l410">        return getBooleanOption(StandardSocketOptions.SO_REUSEADDR);</span><a href="#l410"></a>
<span id="l411">    }</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">    public void close() throws IOException {</span><a href="#l413"></a>
<span id="l414">        sc.close();</span><a href="#l414"></a>
<span id="l415">    }</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">    public void shutdownInput() throws IOException {</span><a href="#l417"></a>
<span id="l418">        try {</span><a href="#l418"></a>
<span id="l419">            sc.shutdownInput();</span><a href="#l419"></a>
<span id="l420">        } catch (Exception x) {</span><a href="#l420"></a>
<span id="l421">            Net.translateException(x);</span><a href="#l421"></a>
<span id="l422">        }</span><a href="#l422"></a>
<span id="l423">    }</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">    public void shutdownOutput() throws IOException {</span><a href="#l425"></a>
<span id="l426">        try {</span><a href="#l426"></a>
<span id="l427">            sc.shutdownOutput();</span><a href="#l427"></a>
<span id="l428">        } catch (Exception x) {</span><a href="#l428"></a>
<span id="l429">            Net.translateException(x);</span><a href="#l429"></a>
<span id="l430">        }</span><a href="#l430"></a>
<span id="l431">    }</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">    public String toString() {</span><a href="#l433"></a>
<span id="l434">        if (sc.isConnected())</span><a href="#l434"></a>
<span id="l435">            return &quot;Socket[addr=&quot; + getInetAddress() +</span><a href="#l435"></a>
<span id="l436">                &quot;,port=&quot; + getPort() +</span><a href="#l436"></a>
<span id="l437">                &quot;,localport=&quot; + getLocalPort() + &quot;]&quot;;</span><a href="#l437"></a>
<span id="l438">        return &quot;Socket[unconnected]&quot;;</span><a href="#l438"></a>
<span id="l439">    }</span><a href="#l439"></a>
<span id="l440"></span><a href="#l440"></a>
<span id="l441">    public boolean isConnected() {</span><a href="#l441"></a>
<span id="l442">        return sc.isConnected();</span><a href="#l442"></a>
<span id="l443">    }</span><a href="#l443"></a>
<span id="l444"></span><a href="#l444"></a>
<span id="l445">    public boolean isBound() {</span><a href="#l445"></a>
<span id="l446">        return sc.localAddress() != null;</span><a href="#l446"></a>
<span id="l447">    }</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">    public boolean isClosed() {</span><a href="#l449"></a>
<span id="l450">        return !sc.isOpen();</span><a href="#l450"></a>
<span id="l451">    }</span><a href="#l451"></a>
<span id="l452"></span><a href="#l452"></a>
<span id="l453">    public boolean isInputShutdown() {</span><a href="#l453"></a>
<span id="l454">        return !sc.isInputOpen();</span><a href="#l454"></a>
<span id="l455">    }</span><a href="#l455"></a>
<span id="l456"></span><a href="#l456"></a>
<span id="l457">    public boolean isOutputShutdown() {</span><a href="#l457"></a>
<span id="l458">        return !sc.isOutputOpen();</span><a href="#l458"></a>
<span id="l459">    }</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">}</span><a href="#l461"></a></pre>
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

