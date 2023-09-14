<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/sun/nio/ch/SocketChannelImpl.java</title>
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
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/nio/ch/SocketChannelImpl.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketChannelImpl.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketChannelImpl.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketChannelImpl.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketChannelImpl.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/sun/nio/ch/SocketChannelImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/nio/ch/SocketChannelImpl.java @ 8936:c9b0a18f082e</h3>

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
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/ff8c7bdc6405/src/share/classes/sun/nio/ch/SocketChannelImpl.java">ff8c7bdc6405</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2012, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.FileDescriptor;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.net.*;</span><a href="#l30"></a>
<span id="l31">import java.nio.ByteBuffer;</span><a href="#l31"></a>
<span id="l32">import java.nio.channels.*;</span><a href="#l32"></a>
<span id="l33">import java.nio.channels.spi.*;</span><a href="#l33"></a>
<span id="l34">import java.util.*;</span><a href="#l34"></a>
<span id="l35">import sun.net.NetHooks;</span><a href="#l35"></a>
<span id="l36">import sun.net.ExtendedOptionsImpl;</span><a href="#l36"></a>
<span id="l37">import sun.misc.IoTrace;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">/**</span><a href="#l39"></a>
<span id="l40"> * An implementation of SocketChannels</span><a href="#l40"></a>
<span id="l41"> */</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">class SocketChannelImpl</span><a href="#l43"></a>
<span id="l44">    extends SocketChannel</span><a href="#l44"></a>
<span id="l45">    implements SelChImpl</span><a href="#l45"></a>
<span id="l46">{</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    // Used to make native read and write calls</span><a href="#l48"></a>
<span id="l49">    private static NativeDispatcher nd;</span><a href="#l49"></a>
<span id="l50"></span><a href="#l50"></a>
<span id="l51">    // Our file descriptor object</span><a href="#l51"></a>
<span id="l52">    private final FileDescriptor fd;</span><a href="#l52"></a>
<span id="l53">    private final int fdVal;</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    // IDs of native threads doing reads and writes, for signalling</span><a href="#l55"></a>
<span id="l56">    private volatile long readerThread = 0;</span><a href="#l56"></a>
<span id="l57">    private volatile long writerThread = 0;</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">    // Lock held by current reading or connecting thread</span><a href="#l59"></a>
<span id="l60">    private final Object readLock = new Object();</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    // Lock held by current writing or connecting thread</span><a href="#l62"></a>
<span id="l63">    private final Object writeLock = new Object();</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    // Lock held by any thread that modifies the state fields declared below</span><a href="#l65"></a>
<span id="l66">    // DO NOT invoke a blocking I/O operation while holding this lock!</span><a href="#l66"></a>
<span id="l67">    private final Object stateLock = new Object();</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    // -- The following fields are protected by stateLock</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    // set true when exclusive binding is on and SO_REUSEADDR is emulated</span><a href="#l71"></a>
<span id="l72">    private boolean isReuseAddress;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    // State, increases monotonically</span><a href="#l74"></a>
<span id="l75">    private static final int ST_UNINITIALIZED = -1;</span><a href="#l75"></a>
<span id="l76">    private static final int ST_UNCONNECTED = 0;</span><a href="#l76"></a>
<span id="l77">    private static final int ST_PENDING = 1;</span><a href="#l77"></a>
<span id="l78">    private static final int ST_CONNECTED = 2;</span><a href="#l78"></a>
<span id="l79">    private static final int ST_KILLPENDING = 3;</span><a href="#l79"></a>
<span id="l80">    private static final int ST_KILLED = 4;</span><a href="#l80"></a>
<span id="l81">    private int state = ST_UNINITIALIZED;</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    // Binding</span><a href="#l83"></a>
<span id="l84">    private InetSocketAddress localAddress;</span><a href="#l84"></a>
<span id="l85">    private InetSocketAddress remoteAddress;</span><a href="#l85"></a>
<span id="l86"></span><a href="#l86"></a>
<span id="l87">    // Input/Output open</span><a href="#l87"></a>
<span id="l88">    private boolean isInputOpen = true;</span><a href="#l88"></a>
<span id="l89">    private boolean isOutputOpen = true;</span><a href="#l89"></a>
<span id="l90">    private boolean readyToConnect = false;</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">    // Socket adaptor, created on demand</span><a href="#l92"></a>
<span id="l93">    private Socket socket;</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">    // -- End of fields protected by stateLock</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    // Constructor for normal connecting sockets</span><a href="#l98"></a>
<span id="l99">    //</span><a href="#l99"></a>
<span id="l100">    SocketChannelImpl(SelectorProvider sp) throws IOException {</span><a href="#l100"></a>
<span id="l101">        super(sp);</span><a href="#l101"></a>
<span id="l102">        this.fd = Net.socket(true);</span><a href="#l102"></a>
<span id="l103">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l103"></a>
<span id="l104">        this.state = ST_UNCONNECTED;</span><a href="#l104"></a>
<span id="l105">    }</span><a href="#l105"></a>
<span id="l106"></span><a href="#l106"></a>
<span id="l107">    SocketChannelImpl(SelectorProvider sp,</span><a href="#l107"></a>
<span id="l108">                      FileDescriptor fd,</span><a href="#l108"></a>
<span id="l109">                      boolean bound)</span><a href="#l109"></a>
<span id="l110">        throws IOException</span><a href="#l110"></a>
<span id="l111">    {</span><a href="#l111"></a>
<span id="l112">        super(sp);</span><a href="#l112"></a>
<span id="l113">        this.fd = fd;</span><a href="#l113"></a>
<span id="l114">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l114"></a>
<span id="l115">        this.state = ST_UNCONNECTED;</span><a href="#l115"></a>
<span id="l116">        if (bound)</span><a href="#l116"></a>
<span id="l117">            this.localAddress = Net.localAddress(fd);</span><a href="#l117"></a>
<span id="l118">    }</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">    // Constructor for sockets obtained from server sockets</span><a href="#l120"></a>
<span id="l121">    //</span><a href="#l121"></a>
<span id="l122">    SocketChannelImpl(SelectorProvider sp,</span><a href="#l122"></a>
<span id="l123">                      FileDescriptor fd, InetSocketAddress remote)</span><a href="#l123"></a>
<span id="l124">        throws IOException</span><a href="#l124"></a>
<span id="l125">    {</span><a href="#l125"></a>
<span id="l126">        super(sp);</span><a href="#l126"></a>
<span id="l127">        this.fd = fd;</span><a href="#l127"></a>
<span id="l128">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l128"></a>
<span id="l129">        this.state = ST_CONNECTED;</span><a href="#l129"></a>
<span id="l130">        this.localAddress = Net.localAddress(fd);</span><a href="#l130"></a>
<span id="l131">        this.remoteAddress = remote;</span><a href="#l131"></a>
<span id="l132">    }</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">    public Socket socket() {</span><a href="#l134"></a>
<span id="l135">        synchronized (stateLock) {</span><a href="#l135"></a>
<span id="l136">            if (socket == null)</span><a href="#l136"></a>
<span id="l137">                socket = SocketAdaptor.create(this);</span><a href="#l137"></a>
<span id="l138">            return socket;</span><a href="#l138"></a>
<span id="l139">        }</span><a href="#l139"></a>
<span id="l140">    }</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">    @Override</span><a href="#l142"></a>
<span id="l143">    public SocketAddress getLocalAddress() throws IOException {</span><a href="#l143"></a>
<span id="l144">        synchronized (stateLock) {</span><a href="#l144"></a>
<span id="l145">            if (!isOpen())</span><a href="#l145"></a>
<span id="l146">                throw new ClosedChannelException();</span><a href="#l146"></a>
<span id="l147">            return  Net.getRevealedLocalAddress(localAddress);</span><a href="#l147"></a>
<span id="l148">        }</span><a href="#l148"></a>
<span id="l149">    }</span><a href="#l149"></a>
<span id="l150"></span><a href="#l150"></a>
<span id="l151">    @Override</span><a href="#l151"></a>
<span id="l152">    public SocketAddress getRemoteAddress() throws IOException {</span><a href="#l152"></a>
<span id="l153">        synchronized (stateLock) {</span><a href="#l153"></a>
<span id="l154">            if (!isOpen())</span><a href="#l154"></a>
<span id="l155">                throw new ClosedChannelException();</span><a href="#l155"></a>
<span id="l156">            return remoteAddress;</span><a href="#l156"></a>
<span id="l157">        }</span><a href="#l157"></a>
<span id="l158">    }</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">    @Override</span><a href="#l160"></a>
<span id="l161">    public &lt;T&gt; SocketChannel setOption(SocketOption&lt;T&gt; name, T value)</span><a href="#l161"></a>
<span id="l162">        throws IOException</span><a href="#l162"></a>
<span id="l163">    {</span><a href="#l163"></a>
<span id="l164">        if (name == null)</span><a href="#l164"></a>
<span id="l165">            throw new NullPointerException();</span><a href="#l165"></a>
<span id="l166">        if (!supportedOptions().contains(name))</span><a href="#l166"></a>
<span id="l167">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">        synchronized (stateLock) {</span><a href="#l169"></a>
<span id="l170">            if (!isOpen())</span><a href="#l170"></a>
<span id="l171">                throw new ClosedChannelException();</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">            if (name == StandardSocketOptions.IP_TOS) {</span><a href="#l173"></a>
<span id="l174">                ProtocolFamily family = Net.isIPv6Available() ?</span><a href="#l174"></a>
<span id="l175">                    StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;</span><a href="#l175"></a>
<span id="l176">                Net.setSocketOption(fd, family, name, value);</span><a href="#l176"></a>
<span id="l177">                return this;</span><a href="#l177"></a>
<span id="l178">            }</span><a href="#l178"></a>
<span id="l179"></span><a href="#l179"></a>
<span id="l180">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp; Net.useExclusiveBind()) {</span><a href="#l180"></a>
<span id="l181">                // SO_REUSEADDR emulated when using exclusive bind</span><a href="#l181"></a>
<span id="l182">                isReuseAddress = (Boolean)value;</span><a href="#l182"></a>
<span id="l183">                return this;</span><a href="#l183"></a>
<span id="l184">            }</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">            // no options that require special handling</span><a href="#l186"></a>
<span id="l187">            Net.setSocketOption(fd, Net.UNSPEC, name, value);</span><a href="#l187"></a>
<span id="l188">            return this;</span><a href="#l188"></a>
<span id="l189">        }</span><a href="#l189"></a>
<span id="l190">    }</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">    @Override</span><a href="#l192"></a>
<span id="l193">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l193"></a>
<span id="l194">    public &lt;T&gt; T getOption(SocketOption&lt;T&gt; name)</span><a href="#l194"></a>
<span id="l195">        throws IOException</span><a href="#l195"></a>
<span id="l196">    {</span><a href="#l196"></a>
<span id="l197">        if (name == null)</span><a href="#l197"></a>
<span id="l198">            throw new NullPointerException();</span><a href="#l198"></a>
<span id="l199">        if (!supportedOptions().contains(name))</span><a href="#l199"></a>
<span id="l200">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">        synchronized (stateLock) {</span><a href="#l202"></a>
<span id="l203">            if (!isOpen())</span><a href="#l203"></a>
<span id="l204">                throw new ClosedChannelException();</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp;</span><a href="#l206"></a>
<span id="l207">                    Net.useExclusiveBind())</span><a href="#l207"></a>
<span id="l208">            {</span><a href="#l208"></a>
<span id="l209">                // SO_REUSEADDR emulated when using exclusive bind</span><a href="#l209"></a>
<span id="l210">                return (T)Boolean.valueOf(isReuseAddress);</span><a href="#l210"></a>
<span id="l211">            }</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">            // special handling for IP_TOS: always return 0 when IPv6</span><a href="#l213"></a>
<span id="l214">            if (name == StandardSocketOptions.IP_TOS) {</span><a href="#l214"></a>
<span id="l215">                ProtocolFamily family = Net.isIPv6Available() ?</span><a href="#l215"></a>
<span id="l216">                    StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;</span><a href="#l216"></a>
<span id="l217">                return (T) Net.getSocketOption(fd, family, name);</span><a href="#l217"></a>
<span id="l218">            }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">            // no options that require special handling</span><a href="#l220"></a>
<span id="l221">            return (T) Net.getSocketOption(fd, Net.UNSPEC, name);</span><a href="#l221"></a>
<span id="l222">        }</span><a href="#l222"></a>
<span id="l223">    }</span><a href="#l223"></a>
<span id="l224"></span><a href="#l224"></a>
<span id="l225">    private static class DefaultOptionsHolder {</span><a href="#l225"></a>
<span id="l226">        static final Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions = defaultOptions();</span><a href="#l226"></a>
<span id="l227"></span><a href="#l227"></a>
<span id="l228">        private static Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions() {</span><a href="#l228"></a>
<span id="l229">            HashSet&lt;SocketOption&lt;?&gt;&gt; set = new HashSet&lt;SocketOption&lt;?&gt;&gt;(8);</span><a href="#l229"></a>
<span id="l230">            set.add(StandardSocketOptions.SO_SNDBUF);</span><a href="#l230"></a>
<span id="l231">            set.add(StandardSocketOptions.SO_RCVBUF);</span><a href="#l231"></a>
<span id="l232">            set.add(StandardSocketOptions.SO_KEEPALIVE);</span><a href="#l232"></a>
<span id="l233">            set.add(StandardSocketOptions.SO_REUSEADDR);</span><a href="#l233"></a>
<span id="l234">            set.add(StandardSocketOptions.SO_LINGER);</span><a href="#l234"></a>
<span id="l235">            set.add(StandardSocketOptions.TCP_NODELAY);</span><a href="#l235"></a>
<span id="l236">            // additional options required by socket adaptor</span><a href="#l236"></a>
<span id="l237">            set.add(StandardSocketOptions.IP_TOS);</span><a href="#l237"></a>
<span id="l238">            set.add(ExtendedSocketOption.SO_OOBINLINE);</span><a href="#l238"></a>
<span id="l239">            if (ExtendedOptionsImpl.flowSupported()) {</span><a href="#l239"></a>
<span id="l240">                set.add(jdk.net.ExtendedSocketOptions.SO_FLOW_SLA);</span><a href="#l240"></a>
<span id="l241">            }</span><a href="#l241"></a>
<span id="l242">            return Collections.unmodifiableSet(set);</span><a href="#l242"></a>
<span id="l243">        }</span><a href="#l243"></a>
<span id="l244">    }</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">    @Override</span><a href="#l246"></a>
<span id="l247">    public final Set&lt;SocketOption&lt;?&gt;&gt; supportedOptions() {</span><a href="#l247"></a>
<span id="l248">        return DefaultOptionsHolder.defaultOptions;</span><a href="#l248"></a>
<span id="l249">    }</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">    private boolean ensureReadOpen() throws ClosedChannelException {</span><a href="#l251"></a>
<span id="l252">        synchronized (stateLock) {</span><a href="#l252"></a>
<span id="l253">            if (!isOpen())</span><a href="#l253"></a>
<span id="l254">                throw new ClosedChannelException();</span><a href="#l254"></a>
<span id="l255">            if (!isConnected())</span><a href="#l255"></a>
<span id="l256">                throw new NotYetConnectedException();</span><a href="#l256"></a>
<span id="l257">            if (!isInputOpen)</span><a href="#l257"></a>
<span id="l258">                return false;</span><a href="#l258"></a>
<span id="l259">            else</span><a href="#l259"></a>
<span id="l260">                return true;</span><a href="#l260"></a>
<span id="l261">        }</span><a href="#l261"></a>
<span id="l262">    }</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">    private void ensureWriteOpen() throws ClosedChannelException {</span><a href="#l264"></a>
<span id="l265">        synchronized (stateLock) {</span><a href="#l265"></a>
<span id="l266">            if (!isOpen())</span><a href="#l266"></a>
<span id="l267">                throw new ClosedChannelException();</span><a href="#l267"></a>
<span id="l268">            if (!isOutputOpen)</span><a href="#l268"></a>
<span id="l269">                throw new ClosedChannelException();</span><a href="#l269"></a>
<span id="l270">            if (!isConnected())</span><a href="#l270"></a>
<span id="l271">                throw new NotYetConnectedException();</span><a href="#l271"></a>
<span id="l272">        }</span><a href="#l272"></a>
<span id="l273">    }</span><a href="#l273"></a>
<span id="l274"></span><a href="#l274"></a>
<span id="l275">    private void readerCleanup() throws IOException {</span><a href="#l275"></a>
<span id="l276">        synchronized (stateLock) {</span><a href="#l276"></a>
<span id="l277">            readerThread = 0;</span><a href="#l277"></a>
<span id="l278">            if (state == ST_KILLPENDING)</span><a href="#l278"></a>
<span id="l279">                kill();</span><a href="#l279"></a>
<span id="l280">        }</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    private void writerCleanup() throws IOException {</span><a href="#l283"></a>
<span id="l284">        synchronized (stateLock) {</span><a href="#l284"></a>
<span id="l285">            writerThread = 0;</span><a href="#l285"></a>
<span id="l286">            if (state == ST_KILLPENDING)</span><a href="#l286"></a>
<span id="l287">                kill();</span><a href="#l287"></a>
<span id="l288">        }</span><a href="#l288"></a>
<span id="l289">    }</span><a href="#l289"></a>
<span id="l290"></span><a href="#l290"></a>
<span id="l291">    public int read(ByteBuffer buf) throws IOException {</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">        if (buf == null)</span><a href="#l293"></a>
<span id="l294">            throw new NullPointerException();</span><a href="#l294"></a>
<span id="l295"></span><a href="#l295"></a>
<span id="l296">        synchronized (readLock) {</span><a href="#l296"></a>
<span id="l297">            if (!ensureReadOpen())</span><a href="#l297"></a>
<span id="l298">                return -1;</span><a href="#l298"></a>
<span id="l299">            Object traceContext = null;</span><a href="#l299"></a>
<span id="l300">            if (isBlocking()) {</span><a href="#l300"></a>
<span id="l301">                traceContext = IoTrace.socketReadBegin();</span><a href="#l301"></a>
<span id="l302">            }</span><a href="#l302"></a>
<span id="l303">            int n = 0;</span><a href="#l303"></a>
<span id="l304">            try {</span><a href="#l304"></a>
<span id="l305"></span><a href="#l305"></a>
<span id="l306">                // Set up the interruption machinery; see</span><a href="#l306"></a>
<span id="l307">                // AbstractInterruptibleChannel for details</span><a href="#l307"></a>
<span id="l308">                //</span><a href="#l308"></a>
<span id="l309">                begin();</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">                synchronized (stateLock) {</span><a href="#l311"></a>
<span id="l312">                    if (!isOpen()) {</span><a href="#l312"></a>
<span id="l313">                    // Either the current thread is already interrupted, so</span><a href="#l313"></a>
<span id="l314">                    // begin() closed the channel, or another thread closed the</span><a href="#l314"></a>
<span id="l315">                    // channel since we checked it a few bytecodes ago.  In</span><a href="#l315"></a>
<span id="l316">                    // either case the value returned here is irrelevant since</span><a href="#l316"></a>
<span id="l317">                    // the invocation of end() in the finally block will throw</span><a href="#l317"></a>
<span id="l318">                    // an appropriate exception.</span><a href="#l318"></a>
<span id="l319">                    //</span><a href="#l319"></a>
<span id="l320">                        return 0;</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">                    }</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">                    // Save this thread so that it can be signalled on those</span><a href="#l324"></a>
<span id="l325">                    // platforms that require it</span><a href="#l325"></a>
<span id="l326">                    //</span><a href="#l326"></a>
<span id="l327">                    readerThread = NativeThread.current();</span><a href="#l327"></a>
<span id="l328">                }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">                // Between the previous test of isOpen() and the return of the</span><a href="#l330"></a>
<span id="l331">                // IOUtil.read invocation below, this channel might be closed</span><a href="#l331"></a>
<span id="l332">                // or this thread might be interrupted.  We rely upon the</span><a href="#l332"></a>
<span id="l333">                // implicit synchronization point in the kernel read() call to</span><a href="#l333"></a>
<span id="l334">                // make sure that the right thing happens.  In either case the</span><a href="#l334"></a>
<span id="l335">                // implCloseSelectableChannel method is ultimately invoked in</span><a href="#l335"></a>
<span id="l336">                // some other thread, so there are three possibilities:</span><a href="#l336"></a>
<span id="l337">                //</span><a href="#l337"></a>
<span id="l338">                //   - implCloseSelectableChannel() invokes nd.preClose()</span><a href="#l338"></a>
<span id="l339">                //     before this thread invokes read(), in which case the</span><a href="#l339"></a>
<span id="l340">                //     read returns immediately with either EOF or an error,</span><a href="#l340"></a>
<span id="l341">                //     the latter of which will cause an IOException to be</span><a href="#l341"></a>
<span id="l342">                //     thrown.</span><a href="#l342"></a>
<span id="l343">                //</span><a href="#l343"></a>
<span id="l344">                //   - implCloseSelectableChannel() invokes nd.preClose() after</span><a href="#l344"></a>
<span id="l345">                //     this thread is blocked in read().  On some operating</span><a href="#l345"></a>
<span id="l346">                //     systems (e.g., Solaris and Windows) this causes the read</span><a href="#l346"></a>
<span id="l347">                //     to return immediately with either EOF or an error</span><a href="#l347"></a>
<span id="l348">                //     indication.</span><a href="#l348"></a>
<span id="l349">                //</span><a href="#l349"></a>
<span id="l350">                //   - implCloseSelectableChannel() invokes nd.preClose() after</span><a href="#l350"></a>
<span id="l351">                //     this thread is blocked in read() but the operating</span><a href="#l351"></a>
<span id="l352">                //     system (e.g., Linux) doesn't support preemptive close,</span><a href="#l352"></a>
<span id="l353">                //     so implCloseSelectableChannel() proceeds to signal this</span><a href="#l353"></a>
<span id="l354">                //     thread, thereby causing the read to return immediately</span><a href="#l354"></a>
<span id="l355">                //     with IOStatus.INTERRUPTED.</span><a href="#l355"></a>
<span id="l356">                //</span><a href="#l356"></a>
<span id="l357">                // In all three cases the invocation of end() in the finally</span><a href="#l357"></a>
<span id="l358">                // clause will notice that the channel has been closed and</span><a href="#l358"></a>
<span id="l359">                // throw an appropriate exception (AsynchronousCloseException</span><a href="#l359"></a>
<span id="l360">                // or ClosedByInterruptException) if necessary.</span><a href="#l360"></a>
<span id="l361">                //</span><a href="#l361"></a>
<span id="l362">                // *There is A fourth possibility. implCloseSelectableChannel()</span><a href="#l362"></a>
<span id="l363">                // invokes nd.preClose(), signals reader/writer thred and quickly</span><a href="#l363"></a>
<span id="l364">                // moves on to nd.close() in kill(), which does a real close.</span><a href="#l364"></a>
<span id="l365">                // Then a third thread accepts a new connection, opens file or</span><a href="#l365"></a>
<span id="l366">                // whatever that causes the released &quot;fd&quot; to be recycled. All</span><a href="#l366"></a>
<span id="l367">                // above happens just between our last isOpen() check and the</span><a href="#l367"></a>
<span id="l368">                // next kernel read reached, with the recycled &quot;fd&quot;. The solution</span><a href="#l368"></a>
<span id="l369">                // is to postpone the real kill() if there is a reader or/and</span><a href="#l369"></a>
<span id="l370">                // writer thread(s) over there &quot;waiting&quot;, leave the cleanup/kill</span><a href="#l370"></a>
<span id="l371">                // to the reader or writer thread. (the preClose() still happens</span><a href="#l371"></a>
<span id="l372">                // so the connection gets cut off as usual).</span><a href="#l372"></a>
<span id="l373">                //</span><a href="#l373"></a>
<span id="l374">                // For socket channels there is the additional wrinkle that</span><a href="#l374"></a>
<span id="l375">                // asynchronous shutdown works much like asynchronous close,</span><a href="#l375"></a>
<span id="l376">                // except that the channel is shutdown rather than completely</span><a href="#l376"></a>
<span id="l377">                // closed.  This is analogous to the first two cases above,</span><a href="#l377"></a>
<span id="l378">                // except that the shutdown operation plays the role of</span><a href="#l378"></a>
<span id="l379">                // nd.preClose().</span><a href="#l379"></a>
<span id="l380">                for (;;) {</span><a href="#l380"></a>
<span id="l381">                    n = IOUtil.read(fd, buf, -1, nd);</span><a href="#l381"></a>
<span id="l382">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen()) {</span><a href="#l382"></a>
<span id="l383">                        // The system call was interrupted but the channel</span><a href="#l383"></a>
<span id="l384">                        // is still open, so retry</span><a href="#l384"></a>
<span id="l385">                        continue;</span><a href="#l385"></a>
<span id="l386">                    }</span><a href="#l386"></a>
<span id="l387">                    return IOStatus.normalize(n);</span><a href="#l387"></a>
<span id="l388">                }</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">            } finally {</span><a href="#l390"></a>
<span id="l391">                readerCleanup();        // Clear reader thread</span><a href="#l391"></a>
<span id="l392"></span><a href="#l392"></a>
<span id="l393">                if (isBlocking()) {</span><a href="#l393"></a>
<span id="l394">                    IoTrace.socketReadEnd(traceContext, remoteAddress.getAddress(),</span><a href="#l394"></a>
<span id="l395">                                          remoteAddress.getPort(), 0, n &gt; 0 ? n : 0);</span><a href="#l395"></a>
<span id="l396">                }</span><a href="#l396"></a>
<span id="l397"></span><a href="#l397"></a>
<span id="l398">                // The end method, which is defined in our superclass</span><a href="#l398"></a>
<span id="l399">                // AbstractInterruptibleChannel, resets the interruption</span><a href="#l399"></a>
<span id="l400">                // machinery.  If its argument is true then it returns</span><a href="#l400"></a>
<span id="l401">                // normally; otherwise it checks the interrupt and open state</span><a href="#l401"></a>
<span id="l402">                // of this channel and throws an appropriate exception if</span><a href="#l402"></a>
<span id="l403">                // necessary.</span><a href="#l403"></a>
<span id="l404">                //</span><a href="#l404"></a>
<span id="l405">                // So, if we actually managed to do any I/O in the above try</span><a href="#l405"></a>
<span id="l406">                // block then we pass true to the end method.  We also pass</span><a href="#l406"></a>
<span id="l407">                // true if the channel was in non-blocking mode when the I/O</span><a href="#l407"></a>
<span id="l408">                // operation was initiated but no data could be transferred;</span><a href="#l408"></a>
<span id="l409">                // this prevents spurious exceptions from being thrown in the</span><a href="#l409"></a>
<span id="l410">                // rare event that a channel is closed or a thread is</span><a href="#l410"></a>
<span id="l411">                // interrupted at the exact moment that a non-blocking I/O</span><a href="#l411"></a>
<span id="l412">                // request is made.</span><a href="#l412"></a>
<span id="l413">                //</span><a href="#l413"></a>
<span id="l414">                end(n &gt; 0 || (n == IOStatus.UNAVAILABLE));</span><a href="#l414"></a>
<span id="l415"></span><a href="#l415"></a>
<span id="l416">                // Extra case for socket channels: Asynchronous shutdown</span><a href="#l416"></a>
<span id="l417">                //</span><a href="#l417"></a>
<span id="l418">                synchronized (stateLock) {</span><a href="#l418"></a>
<span id="l419">                    if ((n &lt;= 0) &amp;&amp; (!isInputOpen))</span><a href="#l419"></a>
<span id="l420">                        return IOStatus.EOF;</span><a href="#l420"></a>
<span id="l421">                }</span><a href="#l421"></a>
<span id="l422"></span><a href="#l422"></a>
<span id="l423">                assert IOStatus.check(n);</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">            }</span><a href="#l425"></a>
<span id="l426">        }</span><a href="#l426"></a>
<span id="l427">    }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">    public long read(ByteBuffer[] dsts, int offset, int length)</span><a href="#l429"></a>
<span id="l430">        throws IOException</span><a href="#l430"></a>
<span id="l431">    {</span><a href="#l431"></a>
<span id="l432">        if ((offset &lt; 0) || (length &lt; 0) || (offset &gt; dsts.length - length))</span><a href="#l432"></a>
<span id="l433">            throw new IndexOutOfBoundsException();</span><a href="#l433"></a>
<span id="l434">        synchronized (readLock) {</span><a href="#l434"></a>
<span id="l435">            if (!ensureReadOpen())</span><a href="#l435"></a>
<span id="l436">                return -1;</span><a href="#l436"></a>
<span id="l437">            long n = 0;</span><a href="#l437"></a>
<span id="l438">            Object traceContext = null;</span><a href="#l438"></a>
<span id="l439">            if (isBlocking()) {</span><a href="#l439"></a>
<span id="l440">                traceContext = IoTrace.socketReadBegin();</span><a href="#l440"></a>
<span id="l441">            }</span><a href="#l441"></a>
<span id="l442">            try {</span><a href="#l442"></a>
<span id="l443">                begin();</span><a href="#l443"></a>
<span id="l444">                synchronized (stateLock) {</span><a href="#l444"></a>
<span id="l445">                    if (!isOpen())</span><a href="#l445"></a>
<span id="l446">                        return 0;</span><a href="#l446"></a>
<span id="l447">                    readerThread = NativeThread.current();</span><a href="#l447"></a>
<span id="l448">                }</span><a href="#l448"></a>
<span id="l449"></span><a href="#l449"></a>
<span id="l450">                for (;;) {</span><a href="#l450"></a>
<span id="l451">                    n = IOUtil.read(fd, dsts, offset, length, nd);</span><a href="#l451"></a>
<span id="l452">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen())</span><a href="#l452"></a>
<span id="l453">                        continue;</span><a href="#l453"></a>
<span id="l454">                    return IOStatus.normalize(n);</span><a href="#l454"></a>
<span id="l455">                }</span><a href="#l455"></a>
<span id="l456">            } finally {</span><a href="#l456"></a>
<span id="l457">                readerCleanup();</span><a href="#l457"></a>
<span id="l458">                if (isBlocking()) {</span><a href="#l458"></a>
<span id="l459">                    IoTrace.socketReadEnd(traceContext, remoteAddress.getAddress(),</span><a href="#l459"></a>
<span id="l460">                                          remoteAddress.getPort(), 0, n &gt; 0 ? n : 0);</span><a href="#l460"></a>
<span id="l461">                }</span><a href="#l461"></a>
<span id="l462">                end(n &gt; 0 || (n == IOStatus.UNAVAILABLE));</span><a href="#l462"></a>
<span id="l463">                synchronized (stateLock) {</span><a href="#l463"></a>
<span id="l464">                    if ((n &lt;= 0) &amp;&amp; (!isInputOpen))</span><a href="#l464"></a>
<span id="l465">                        return IOStatus.EOF;</span><a href="#l465"></a>
<span id="l466">                }</span><a href="#l466"></a>
<span id="l467">                assert IOStatus.check(n);</span><a href="#l467"></a>
<span id="l468">            }</span><a href="#l468"></a>
<span id="l469">        }</span><a href="#l469"></a>
<span id="l470">    }</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">    public int write(ByteBuffer buf) throws IOException {</span><a href="#l472"></a>
<span id="l473">        if (buf == null)</span><a href="#l473"></a>
<span id="l474">            throw new NullPointerException();</span><a href="#l474"></a>
<span id="l475">        synchronized (writeLock) {</span><a href="#l475"></a>
<span id="l476">            ensureWriteOpen();</span><a href="#l476"></a>
<span id="l477">            int n = 0;</span><a href="#l477"></a>
<span id="l478">            Object traceContext =</span><a href="#l478"></a>
<span id="l479">                IoTrace.socketWriteBegin();</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481">            try {</span><a href="#l481"></a>
<span id="l482">                begin();</span><a href="#l482"></a>
<span id="l483">                synchronized (stateLock) {</span><a href="#l483"></a>
<span id="l484">                    if (!isOpen())</span><a href="#l484"></a>
<span id="l485">                        return 0;</span><a href="#l485"></a>
<span id="l486">                    writerThread = NativeThread.current();</span><a href="#l486"></a>
<span id="l487">                }</span><a href="#l487"></a>
<span id="l488">                for (;;) {</span><a href="#l488"></a>
<span id="l489">                    n = IOUtil.write(fd, buf, -1, nd);</span><a href="#l489"></a>
<span id="l490">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen())</span><a href="#l490"></a>
<span id="l491">                        continue;</span><a href="#l491"></a>
<span id="l492">                    return IOStatus.normalize(n);</span><a href="#l492"></a>
<span id="l493">                }</span><a href="#l493"></a>
<span id="l494">            } finally {</span><a href="#l494"></a>
<span id="l495">                writerCleanup();</span><a href="#l495"></a>
<span id="l496">                IoTrace.socketWriteEnd(traceContext, remoteAddress.getAddress(),</span><a href="#l496"></a>
<span id="l497">                                       remoteAddress.getPort(), n &gt; 0 ? n : 0);</span><a href="#l497"></a>
<span id="l498">                end(n &gt; 0 || (n == IOStatus.UNAVAILABLE));</span><a href="#l498"></a>
<span id="l499">                synchronized (stateLock) {</span><a href="#l499"></a>
<span id="l500">                    if ((n &lt;= 0) &amp;&amp; (!isOutputOpen))</span><a href="#l500"></a>
<span id="l501">                        throw new AsynchronousCloseException();</span><a href="#l501"></a>
<span id="l502">                }</span><a href="#l502"></a>
<span id="l503">                assert IOStatus.check(n);</span><a href="#l503"></a>
<span id="l504">            }</span><a href="#l504"></a>
<span id="l505">        }</span><a href="#l505"></a>
<span id="l506">    }</span><a href="#l506"></a>
<span id="l507"></span><a href="#l507"></a>
<span id="l508">    public long write(ByteBuffer[] srcs, int offset, int length)</span><a href="#l508"></a>
<span id="l509">        throws IOException</span><a href="#l509"></a>
<span id="l510">    {</span><a href="#l510"></a>
<span id="l511">        if ((offset &lt; 0) || (length &lt; 0) || (offset &gt; srcs.length - length))</span><a href="#l511"></a>
<span id="l512">            throw new IndexOutOfBoundsException();</span><a href="#l512"></a>
<span id="l513">        synchronized (writeLock) {</span><a href="#l513"></a>
<span id="l514">            ensureWriteOpen();</span><a href="#l514"></a>
<span id="l515">            long n = 0;</span><a href="#l515"></a>
<span id="l516">            Object traceContext =</span><a href="#l516"></a>
<span id="l517">                IoTrace.socketWriteBegin();</span><a href="#l517"></a>
<span id="l518">            try {</span><a href="#l518"></a>
<span id="l519">                begin();</span><a href="#l519"></a>
<span id="l520">                synchronized (stateLock) {</span><a href="#l520"></a>
<span id="l521">                    if (!isOpen())</span><a href="#l521"></a>
<span id="l522">                        return 0;</span><a href="#l522"></a>
<span id="l523">                    writerThread = NativeThread.current();</span><a href="#l523"></a>
<span id="l524">                }</span><a href="#l524"></a>
<span id="l525">                for (;;) {</span><a href="#l525"></a>
<span id="l526">                    n = IOUtil.write(fd, srcs, offset, length, nd);</span><a href="#l526"></a>
<span id="l527">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen())</span><a href="#l527"></a>
<span id="l528">                        continue;</span><a href="#l528"></a>
<span id="l529">                    return IOStatus.normalize(n);</span><a href="#l529"></a>
<span id="l530">                }</span><a href="#l530"></a>
<span id="l531">            } finally {</span><a href="#l531"></a>
<span id="l532">                writerCleanup();</span><a href="#l532"></a>
<span id="l533">                IoTrace.socketWriteEnd(traceContext, remoteAddress.getAddress(),</span><a href="#l533"></a>
<span id="l534">                                       remoteAddress.getPort(), n &gt; 0 ? n : 0);</span><a href="#l534"></a>
<span id="l535">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l535"></a>
<span id="l536">                synchronized (stateLock) {</span><a href="#l536"></a>
<span id="l537">                    if ((n &lt;= 0) &amp;&amp; (!isOutputOpen))</span><a href="#l537"></a>
<span id="l538">                        throw new AsynchronousCloseException();</span><a href="#l538"></a>
<span id="l539">                }</span><a href="#l539"></a>
<span id="l540">                assert IOStatus.check(n);</span><a href="#l540"></a>
<span id="l541">            }</span><a href="#l541"></a>
<span id="l542">        }</span><a href="#l542"></a>
<span id="l543">    }</span><a href="#l543"></a>
<span id="l544"></span><a href="#l544"></a>
<span id="l545">    // package-private</span><a href="#l545"></a>
<span id="l546">    int sendOutOfBandData(byte b) throws IOException {</span><a href="#l546"></a>
<span id="l547">        synchronized (writeLock) {</span><a href="#l547"></a>
<span id="l548">            ensureWriteOpen();</span><a href="#l548"></a>
<span id="l549">            int n = 0;</span><a href="#l549"></a>
<span id="l550">            try {</span><a href="#l550"></a>
<span id="l551">                begin();</span><a href="#l551"></a>
<span id="l552">                synchronized (stateLock) {</span><a href="#l552"></a>
<span id="l553">                    if (!isOpen())</span><a href="#l553"></a>
<span id="l554">                        return 0;</span><a href="#l554"></a>
<span id="l555">                    writerThread = NativeThread.current();</span><a href="#l555"></a>
<span id="l556">                }</span><a href="#l556"></a>
<span id="l557">                for (;;) {</span><a href="#l557"></a>
<span id="l558">                    n = sendOutOfBandData(fd, b);</span><a href="#l558"></a>
<span id="l559">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen())</span><a href="#l559"></a>
<span id="l560">                        continue;</span><a href="#l560"></a>
<span id="l561">                    return IOStatus.normalize(n);</span><a href="#l561"></a>
<span id="l562">                }</span><a href="#l562"></a>
<span id="l563">            } finally {</span><a href="#l563"></a>
<span id="l564">                writerCleanup();</span><a href="#l564"></a>
<span id="l565">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l565"></a>
<span id="l566">                synchronized (stateLock) {</span><a href="#l566"></a>
<span id="l567">                    if ((n &lt;= 0) &amp;&amp; (!isOutputOpen))</span><a href="#l567"></a>
<span id="l568">                        throw new AsynchronousCloseException();</span><a href="#l568"></a>
<span id="l569">                }</span><a href="#l569"></a>
<span id="l570">                assert IOStatus.check(n);</span><a href="#l570"></a>
<span id="l571">            }</span><a href="#l571"></a>
<span id="l572">        }</span><a href="#l572"></a>
<span id="l573">    }</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">    protected void implConfigureBlocking(boolean block) throws IOException {</span><a href="#l575"></a>
<span id="l576">        IOUtil.configureBlocking(fd, block);</span><a href="#l576"></a>
<span id="l577">    }</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">    public InetSocketAddress localAddress() {</span><a href="#l579"></a>
<span id="l580">        synchronized (stateLock) {</span><a href="#l580"></a>
<span id="l581">            return localAddress;</span><a href="#l581"></a>
<span id="l582">        }</span><a href="#l582"></a>
<span id="l583">    }</span><a href="#l583"></a>
<span id="l584"></span><a href="#l584"></a>
<span id="l585">    public SocketAddress remoteAddress() {</span><a href="#l585"></a>
<span id="l586">        synchronized (stateLock) {</span><a href="#l586"></a>
<span id="l587">            return remoteAddress;</span><a href="#l587"></a>
<span id="l588">        }</span><a href="#l588"></a>
<span id="l589">    }</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">    @Override</span><a href="#l591"></a>
<span id="l592">    public SocketChannel bind(SocketAddress local) throws IOException {</span><a href="#l592"></a>
<span id="l593">        synchronized (readLock) {</span><a href="#l593"></a>
<span id="l594">            synchronized (writeLock) {</span><a href="#l594"></a>
<span id="l595">                synchronized (stateLock) {</span><a href="#l595"></a>
<span id="l596">                    if (!isOpen())</span><a href="#l596"></a>
<span id="l597">                        throw new ClosedChannelException();</span><a href="#l597"></a>
<span id="l598">                    if (state == ST_PENDING)</span><a href="#l598"></a>
<span id="l599">                        throw new ConnectionPendingException();</span><a href="#l599"></a>
<span id="l600">                    if (localAddress != null)</span><a href="#l600"></a>
<span id="l601">                        throw new AlreadyBoundException();</span><a href="#l601"></a>
<span id="l602">                    InetSocketAddress isa = (local == null) ?</span><a href="#l602"></a>
<span id="l603">                        new InetSocketAddress(0) : Net.checkAddress(local);</span><a href="#l603"></a>
<span id="l604">                    SecurityManager sm = System.getSecurityManager();</span><a href="#l604"></a>
<span id="l605">                    if (sm != null) {</span><a href="#l605"></a>
<span id="l606">                        sm.checkListen(isa.getPort());</span><a href="#l606"></a>
<span id="l607">                    }</span><a href="#l607"></a>
<span id="l608">                    NetHooks.beforeTcpBind(fd, isa.getAddress(), isa.getPort());</span><a href="#l608"></a>
<span id="l609">                    Net.bind(fd, isa.getAddress(), isa.getPort());</span><a href="#l609"></a>
<span id="l610">                    localAddress = Net.localAddress(fd);</span><a href="#l610"></a>
<span id="l611">                }</span><a href="#l611"></a>
<span id="l612">            }</span><a href="#l612"></a>
<span id="l613">        }</span><a href="#l613"></a>
<span id="l614">        return this;</span><a href="#l614"></a>
<span id="l615">    }</span><a href="#l615"></a>
<span id="l616"></span><a href="#l616"></a>
<span id="l617">    public boolean isConnected() {</span><a href="#l617"></a>
<span id="l618">        synchronized (stateLock) {</span><a href="#l618"></a>
<span id="l619">            return (state == ST_CONNECTED);</span><a href="#l619"></a>
<span id="l620">        }</span><a href="#l620"></a>
<span id="l621">    }</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">    public boolean isConnectionPending() {</span><a href="#l623"></a>
<span id="l624">        synchronized (stateLock) {</span><a href="#l624"></a>
<span id="l625">            return (state == ST_PENDING);</span><a href="#l625"></a>
<span id="l626">        }</span><a href="#l626"></a>
<span id="l627">    }</span><a href="#l627"></a>
<span id="l628"></span><a href="#l628"></a>
<span id="l629">    void ensureOpenAndUnconnected() throws IOException { // package-private</span><a href="#l629"></a>
<span id="l630">        synchronized (stateLock) {</span><a href="#l630"></a>
<span id="l631">            if (!isOpen())</span><a href="#l631"></a>
<span id="l632">                throw new ClosedChannelException();</span><a href="#l632"></a>
<span id="l633">            if (state == ST_CONNECTED)</span><a href="#l633"></a>
<span id="l634">                throw new AlreadyConnectedException();</span><a href="#l634"></a>
<span id="l635">            if (state == ST_PENDING)</span><a href="#l635"></a>
<span id="l636">                throw new ConnectionPendingException();</span><a href="#l636"></a>
<span id="l637">        }</span><a href="#l637"></a>
<span id="l638">    }</span><a href="#l638"></a>
<span id="l639"></span><a href="#l639"></a>
<span id="l640">    public boolean connect(SocketAddress sa) throws IOException {</span><a href="#l640"></a>
<span id="l641">        int localPort = 0;</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">        synchronized (readLock) {</span><a href="#l643"></a>
<span id="l644">            synchronized (writeLock) {</span><a href="#l644"></a>
<span id="l645">                ensureOpenAndUnconnected();</span><a href="#l645"></a>
<span id="l646">                InetSocketAddress isa = Net.checkAddress(sa);</span><a href="#l646"></a>
<span id="l647">                SecurityManager sm = System.getSecurityManager();</span><a href="#l647"></a>
<span id="l648">                if (sm != null)</span><a href="#l648"></a>
<span id="l649">                    sm.checkConnect(isa.getAddress().getHostAddress(),</span><a href="#l649"></a>
<span id="l650">                                    isa.getPort());</span><a href="#l650"></a>
<span id="l651">                synchronized (blockingLock()) {</span><a href="#l651"></a>
<span id="l652">                    int n = 0;</span><a href="#l652"></a>
<span id="l653">                    try {</span><a href="#l653"></a>
<span id="l654">                        try {</span><a href="#l654"></a>
<span id="l655">                            begin();</span><a href="#l655"></a>
<span id="l656">                            synchronized (stateLock) {</span><a href="#l656"></a>
<span id="l657">                                if (!isOpen()) {</span><a href="#l657"></a>
<span id="l658">                                    return false;</span><a href="#l658"></a>
<span id="l659">                                }</span><a href="#l659"></a>
<span id="l660">                                // notify hook only if unbound</span><a href="#l660"></a>
<span id="l661">                                if (localAddress == null) {</span><a href="#l661"></a>
<span id="l662">                                    NetHooks.beforeTcpConnect(fd,</span><a href="#l662"></a>
<span id="l663">                                                           isa.getAddress(),</span><a href="#l663"></a>
<span id="l664">                                                           isa.getPort());</span><a href="#l664"></a>
<span id="l665">                                }</span><a href="#l665"></a>
<span id="l666">                                readerThread = NativeThread.current();</span><a href="#l666"></a>
<span id="l667">                            }</span><a href="#l667"></a>
<span id="l668">                            for (;;) {</span><a href="#l668"></a>
<span id="l669">                                InetAddress ia = isa.getAddress();</span><a href="#l669"></a>
<span id="l670">                                if (ia.isAnyLocalAddress())</span><a href="#l670"></a>
<span id="l671">                                    ia = InetAddress.getLocalHost();</span><a href="#l671"></a>
<span id="l672">                                n = Net.connect(fd,</span><a href="#l672"></a>
<span id="l673">                                                ia,</span><a href="#l673"></a>
<span id="l674">                                                isa.getPort());</span><a href="#l674"></a>
<span id="l675">                                if (  (n == IOStatus.INTERRUPTED)</span><a href="#l675"></a>
<span id="l676">                                      &amp;&amp; isOpen())</span><a href="#l676"></a>
<span id="l677">                                    continue;</span><a href="#l677"></a>
<span id="l678">                                break;</span><a href="#l678"></a>
<span id="l679">                            }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">                        } finally {</span><a href="#l681"></a>
<span id="l682">                            readerCleanup();</span><a href="#l682"></a>
<span id="l683">                            end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l683"></a>
<span id="l684">                            assert IOStatus.check(n);</span><a href="#l684"></a>
<span id="l685">                        }</span><a href="#l685"></a>
<span id="l686">                    } catch (IOException x) {</span><a href="#l686"></a>
<span id="l687">                        // If an exception was thrown, close the channel after</span><a href="#l687"></a>
<span id="l688">                        // invoking end() so as to avoid bogus</span><a href="#l688"></a>
<span id="l689">                        // AsynchronousCloseExceptions</span><a href="#l689"></a>
<span id="l690">                        close();</span><a href="#l690"></a>
<span id="l691">                        throw x;</span><a href="#l691"></a>
<span id="l692">                    }</span><a href="#l692"></a>
<span id="l693">                    synchronized (stateLock) {</span><a href="#l693"></a>
<span id="l694">                        remoteAddress = isa;</span><a href="#l694"></a>
<span id="l695">                        if (n &gt; 0) {</span><a href="#l695"></a>
<span id="l696"></span><a href="#l696"></a>
<span id="l697">                            // Connection succeeded; disallow further</span><a href="#l697"></a>
<span id="l698">                            // invocation</span><a href="#l698"></a>
<span id="l699">                            state = ST_CONNECTED;</span><a href="#l699"></a>
<span id="l700">                            if (isOpen())</span><a href="#l700"></a>
<span id="l701">                                localAddress = Net.localAddress(fd);</span><a href="#l701"></a>
<span id="l702">                            return true;</span><a href="#l702"></a>
<span id="l703">                        }</span><a href="#l703"></a>
<span id="l704">                        // If nonblocking and no exception then connection</span><a href="#l704"></a>
<span id="l705">                        // pending; disallow another invocation</span><a href="#l705"></a>
<span id="l706">                        if (!isBlocking())</span><a href="#l706"></a>
<span id="l707">                            state = ST_PENDING;</span><a href="#l707"></a>
<span id="l708">                        else</span><a href="#l708"></a>
<span id="l709">                            assert false;</span><a href="#l709"></a>
<span id="l710">                    }</span><a href="#l710"></a>
<span id="l711">                }</span><a href="#l711"></a>
<span id="l712">                return false;</span><a href="#l712"></a>
<span id="l713">            }</span><a href="#l713"></a>
<span id="l714">        }</span><a href="#l714"></a>
<span id="l715">    }</span><a href="#l715"></a>
<span id="l716"></span><a href="#l716"></a>
<span id="l717">    public boolean finishConnect() throws IOException {</span><a href="#l717"></a>
<span id="l718">        synchronized (readLock) {</span><a href="#l718"></a>
<span id="l719">            synchronized (writeLock) {</span><a href="#l719"></a>
<span id="l720">                synchronized (stateLock) {</span><a href="#l720"></a>
<span id="l721">                    if (!isOpen())</span><a href="#l721"></a>
<span id="l722">                        throw new ClosedChannelException();</span><a href="#l722"></a>
<span id="l723">                    if (state == ST_CONNECTED)</span><a href="#l723"></a>
<span id="l724">                        return true;</span><a href="#l724"></a>
<span id="l725">                    if (state != ST_PENDING)</span><a href="#l725"></a>
<span id="l726">                        throw new NoConnectionPendingException();</span><a href="#l726"></a>
<span id="l727">                }</span><a href="#l727"></a>
<span id="l728">                int n = 0;</span><a href="#l728"></a>
<span id="l729">                try {</span><a href="#l729"></a>
<span id="l730">                    try {</span><a href="#l730"></a>
<span id="l731">                        begin();</span><a href="#l731"></a>
<span id="l732">                        synchronized (blockingLock()) {</span><a href="#l732"></a>
<span id="l733">                            synchronized (stateLock) {</span><a href="#l733"></a>
<span id="l734">                                if (!isOpen()) {</span><a href="#l734"></a>
<span id="l735">                                    return false;</span><a href="#l735"></a>
<span id="l736">                                }</span><a href="#l736"></a>
<span id="l737">                                readerThread = NativeThread.current();</span><a href="#l737"></a>
<span id="l738">                            }</span><a href="#l738"></a>
<span id="l739">                            if (!isBlocking()) {</span><a href="#l739"></a>
<span id="l740">                                for (;;) {</span><a href="#l740"></a>
<span id="l741">                                    n = checkConnect(fd, false,</span><a href="#l741"></a>
<span id="l742">                                                     readyToConnect);</span><a href="#l742"></a>
<span id="l743">                                    if (  (n == IOStatus.INTERRUPTED)</span><a href="#l743"></a>
<span id="l744">                                          &amp;&amp; isOpen())</span><a href="#l744"></a>
<span id="l745">                                        continue;</span><a href="#l745"></a>
<span id="l746">                                    break;</span><a href="#l746"></a>
<span id="l747">                                }</span><a href="#l747"></a>
<span id="l748">                            } else {</span><a href="#l748"></a>
<span id="l749">                                for (;;) {</span><a href="#l749"></a>
<span id="l750">                                    n = checkConnect(fd, true,</span><a href="#l750"></a>
<span id="l751">                                                     readyToConnect);</span><a href="#l751"></a>
<span id="l752">                                    if (n == 0) {</span><a href="#l752"></a>
<span id="l753">                                        // Loop in case of</span><a href="#l753"></a>
<span id="l754">                                        // spurious notifications</span><a href="#l754"></a>
<span id="l755">                                        continue;</span><a href="#l755"></a>
<span id="l756">                                    }</span><a href="#l756"></a>
<span id="l757">                                    if (  (n == IOStatus.INTERRUPTED)</span><a href="#l757"></a>
<span id="l758">                                          &amp;&amp; isOpen())</span><a href="#l758"></a>
<span id="l759">                                        continue;</span><a href="#l759"></a>
<span id="l760">                                    break;</span><a href="#l760"></a>
<span id="l761">                                }</span><a href="#l761"></a>
<span id="l762">                            }</span><a href="#l762"></a>
<span id="l763">                        }</span><a href="#l763"></a>
<span id="l764">                    } finally {</span><a href="#l764"></a>
<span id="l765">                        synchronized (stateLock) {</span><a href="#l765"></a>
<span id="l766">                            readerThread = 0;</span><a href="#l766"></a>
<span id="l767">                            if (state == ST_KILLPENDING) {</span><a href="#l767"></a>
<span id="l768">                                kill();</span><a href="#l768"></a>
<span id="l769">                                // poll()/getsockopt() does not report</span><a href="#l769"></a>
<span id="l770">                                // error (throws exception, with n = 0)</span><a href="#l770"></a>
<span id="l771">                                // on Linux platform after dup2 and</span><a href="#l771"></a>
<span id="l772">                                // signal-wakeup. Force n to 0 so the</span><a href="#l772"></a>
<span id="l773">                                // end() can throw appropriate exception</span><a href="#l773"></a>
<span id="l774">                                n = 0;</span><a href="#l774"></a>
<span id="l775">                            }</span><a href="#l775"></a>
<span id="l776">                        }</span><a href="#l776"></a>
<span id="l777">                        end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l777"></a>
<span id="l778">                        assert IOStatus.check(n);</span><a href="#l778"></a>
<span id="l779">                    }</span><a href="#l779"></a>
<span id="l780">                } catch (IOException x) {</span><a href="#l780"></a>
<span id="l781">                    // If an exception was thrown, close the channel after</span><a href="#l781"></a>
<span id="l782">                    // invoking end() so as to avoid bogus</span><a href="#l782"></a>
<span id="l783">                    // AsynchronousCloseExceptions</span><a href="#l783"></a>
<span id="l784">                    close();</span><a href="#l784"></a>
<span id="l785">                    throw x;</span><a href="#l785"></a>
<span id="l786">                }</span><a href="#l786"></a>
<span id="l787">                if (n &gt; 0) {</span><a href="#l787"></a>
<span id="l788">                    synchronized (stateLock) {</span><a href="#l788"></a>
<span id="l789">                        state = ST_CONNECTED;</span><a href="#l789"></a>
<span id="l790">                        if (isOpen())</span><a href="#l790"></a>
<span id="l791">                            localAddress = Net.localAddress(fd);</span><a href="#l791"></a>
<span id="l792">                    }</span><a href="#l792"></a>
<span id="l793">                    return true;</span><a href="#l793"></a>
<span id="l794">                }</span><a href="#l794"></a>
<span id="l795">                return false;</span><a href="#l795"></a>
<span id="l796">            }</span><a href="#l796"></a>
<span id="l797">        }</span><a href="#l797"></a>
<span id="l798">    }</span><a href="#l798"></a>
<span id="l799"></span><a href="#l799"></a>
<span id="l800">    @Override</span><a href="#l800"></a>
<span id="l801">    public SocketChannel shutdownInput() throws IOException {</span><a href="#l801"></a>
<span id="l802">        synchronized (stateLock) {</span><a href="#l802"></a>
<span id="l803">            if (!isOpen())</span><a href="#l803"></a>
<span id="l804">                throw new ClosedChannelException();</span><a href="#l804"></a>
<span id="l805">            if (!isConnected())</span><a href="#l805"></a>
<span id="l806">                throw new NotYetConnectedException();</span><a href="#l806"></a>
<span id="l807">            if (isInputOpen) {</span><a href="#l807"></a>
<span id="l808">                Net.shutdown(fd, Net.SHUT_RD);</span><a href="#l808"></a>
<span id="l809">                if (readerThread != 0)</span><a href="#l809"></a>
<span id="l810">                    NativeThread.signal(readerThread);</span><a href="#l810"></a>
<span id="l811">                isInputOpen = false;</span><a href="#l811"></a>
<span id="l812">            }</span><a href="#l812"></a>
<span id="l813">            return this;</span><a href="#l813"></a>
<span id="l814">        }</span><a href="#l814"></a>
<span id="l815">    }</span><a href="#l815"></a>
<span id="l816"></span><a href="#l816"></a>
<span id="l817">    @Override</span><a href="#l817"></a>
<span id="l818">    public SocketChannel shutdownOutput() throws IOException {</span><a href="#l818"></a>
<span id="l819">        synchronized (stateLock) {</span><a href="#l819"></a>
<span id="l820">            if (!isOpen())</span><a href="#l820"></a>
<span id="l821">                throw new ClosedChannelException();</span><a href="#l821"></a>
<span id="l822">            if (!isConnected())</span><a href="#l822"></a>
<span id="l823">                throw new NotYetConnectedException();</span><a href="#l823"></a>
<span id="l824">            if (isOutputOpen) {</span><a href="#l824"></a>
<span id="l825">                Net.shutdown(fd, Net.SHUT_WR);</span><a href="#l825"></a>
<span id="l826">                if (writerThread != 0)</span><a href="#l826"></a>
<span id="l827">                    NativeThread.signal(writerThread);</span><a href="#l827"></a>
<span id="l828">                isOutputOpen = false;</span><a href="#l828"></a>
<span id="l829">            }</span><a href="#l829"></a>
<span id="l830">            return this;</span><a href="#l830"></a>
<span id="l831">        }</span><a href="#l831"></a>
<span id="l832">    }</span><a href="#l832"></a>
<span id="l833"></span><a href="#l833"></a>
<span id="l834">    public boolean isInputOpen() {</span><a href="#l834"></a>
<span id="l835">        synchronized (stateLock) {</span><a href="#l835"></a>
<span id="l836">            return isInputOpen;</span><a href="#l836"></a>
<span id="l837">        }</span><a href="#l837"></a>
<span id="l838">    }</span><a href="#l838"></a>
<span id="l839"></span><a href="#l839"></a>
<span id="l840">    public boolean isOutputOpen() {</span><a href="#l840"></a>
<span id="l841">        synchronized (stateLock) {</span><a href="#l841"></a>
<span id="l842">            return isOutputOpen;</span><a href="#l842"></a>
<span id="l843">        }</span><a href="#l843"></a>
<span id="l844">    }</span><a href="#l844"></a>
<span id="l845"></span><a href="#l845"></a>
<span id="l846">    // AbstractInterruptibleChannel synchronizes invocations of this method</span><a href="#l846"></a>
<span id="l847">    // using AbstractInterruptibleChannel.closeLock, and also ensures that this</span><a href="#l847"></a>
<span id="l848">    // method is only ever invoked once.  Before we get to this method, isOpen</span><a href="#l848"></a>
<span id="l849">    // (which is volatile) will have been set to false.</span><a href="#l849"></a>
<span id="l850">    //</span><a href="#l850"></a>
<span id="l851">    protected void implCloseSelectableChannel() throws IOException {</span><a href="#l851"></a>
<span id="l852">        synchronized (stateLock) {</span><a href="#l852"></a>
<span id="l853">            isInputOpen = false;</span><a href="#l853"></a>
<span id="l854">            isOutputOpen = false;</span><a href="#l854"></a>
<span id="l855"></span><a href="#l855"></a>
<span id="l856">            // Close the underlying file descriptor and dup it to a known fd</span><a href="#l856"></a>
<span id="l857">            // that's already closed.  This prevents other operations on this</span><a href="#l857"></a>
<span id="l858">            // channel from using the old fd, which might be recycled in the</span><a href="#l858"></a>
<span id="l859">            // meantime and allocated to an entirely different channel.</span><a href="#l859"></a>
<span id="l860">            //</span><a href="#l860"></a>
<span id="l861">            if (state != ST_KILLED)</span><a href="#l861"></a>
<span id="l862">                nd.preClose(fd);</span><a href="#l862"></a>
<span id="l863"></span><a href="#l863"></a>
<span id="l864">            // Signal native threads, if needed.  If a target thread is not</span><a href="#l864"></a>
<span id="l865">            // currently blocked in an I/O operation then no harm is done since</span><a href="#l865"></a>
<span id="l866">            // the signal handler doesn't actually do anything.</span><a href="#l866"></a>
<span id="l867">            //</span><a href="#l867"></a>
<span id="l868">            if (readerThread != 0)</span><a href="#l868"></a>
<span id="l869">                NativeThread.signal(readerThread);</span><a href="#l869"></a>
<span id="l870"></span><a href="#l870"></a>
<span id="l871">            if (writerThread != 0)</span><a href="#l871"></a>
<span id="l872">                NativeThread.signal(writerThread);</span><a href="#l872"></a>
<span id="l873"></span><a href="#l873"></a>
<span id="l874">            // If this channel is not registered then it's safe to close the fd</span><a href="#l874"></a>
<span id="l875">            // immediately since we know at this point that no thread is</span><a href="#l875"></a>
<span id="l876">            // blocked in an I/O operation upon the channel and, since the</span><a href="#l876"></a>
<span id="l877">            // channel is marked closed, no thread will start another such</span><a href="#l877"></a>
<span id="l878">            // operation.  If this channel is registered then we don't close</span><a href="#l878"></a>
<span id="l879">            // the fd since it might be in use by a selector.  In that case</span><a href="#l879"></a>
<span id="l880">            // closing this channel caused its keys to be cancelled, so the</span><a href="#l880"></a>
<span id="l881">            // last selector to deregister a key for this channel will invoke</span><a href="#l881"></a>
<span id="l882">            // kill() to close the fd.</span><a href="#l882"></a>
<span id="l883">            //</span><a href="#l883"></a>
<span id="l884">            if (!isRegistered())</span><a href="#l884"></a>
<span id="l885">                kill();</span><a href="#l885"></a>
<span id="l886">        }</span><a href="#l886"></a>
<span id="l887">    }</span><a href="#l887"></a>
<span id="l888"></span><a href="#l888"></a>
<span id="l889">    public void kill() throws IOException {</span><a href="#l889"></a>
<span id="l890">        synchronized (stateLock) {</span><a href="#l890"></a>
<span id="l891">            if (state == ST_KILLED)</span><a href="#l891"></a>
<span id="l892">                return;</span><a href="#l892"></a>
<span id="l893">            if (state == ST_UNINITIALIZED) {</span><a href="#l893"></a>
<span id="l894">                state = ST_KILLED;</span><a href="#l894"></a>
<span id="l895">                return;</span><a href="#l895"></a>
<span id="l896">            }</span><a href="#l896"></a>
<span id="l897">            assert !isOpen() &amp;&amp; !isRegistered();</span><a href="#l897"></a>
<span id="l898"></span><a href="#l898"></a>
<span id="l899">            // Postpone the kill if there is a waiting reader</span><a href="#l899"></a>
<span id="l900">            // or writer thread. See the comments in read() for</span><a href="#l900"></a>
<span id="l901">            // more detailed explanation.</span><a href="#l901"></a>
<span id="l902">            if (readerThread == 0 &amp;&amp; writerThread == 0) {</span><a href="#l902"></a>
<span id="l903">                nd.close(fd);</span><a href="#l903"></a>
<span id="l904">                state = ST_KILLED;</span><a href="#l904"></a>
<span id="l905">            } else {</span><a href="#l905"></a>
<span id="l906">                state = ST_KILLPENDING;</span><a href="#l906"></a>
<span id="l907">            }</span><a href="#l907"></a>
<span id="l908">        }</span><a href="#l908"></a>
<span id="l909">    }</span><a href="#l909"></a>
<span id="l910"></span><a href="#l910"></a>
<span id="l911">    /**</span><a href="#l911"></a>
<span id="l912">     * Translates native poll revent ops into a ready operation ops</span><a href="#l912"></a>
<span id="l913">     */</span><a href="#l913"></a>
<span id="l914">    public boolean translateReadyOps(int ops, int initialOps,</span><a href="#l914"></a>
<span id="l915">                                     SelectionKeyImpl sk) {</span><a href="#l915"></a>
<span id="l916">        int intOps = sk.nioInterestOps(); // Do this just once, it synchronizes</span><a href="#l916"></a>
<span id="l917">        int oldOps = sk.nioReadyOps();</span><a href="#l917"></a>
<span id="l918">        int newOps = initialOps;</span><a href="#l918"></a>
<span id="l919"></span><a href="#l919"></a>
<span id="l920">        if ((ops &amp; Net.POLLNVAL) != 0) {</span><a href="#l920"></a>
<span id="l921">            // This should only happen if this channel is pre-closed while a</span><a href="#l921"></a>
<span id="l922">            // selection operation is in progress</span><a href="#l922"></a>
<span id="l923">            // ## Throw an error if this channel has not been pre-closed</span><a href="#l923"></a>
<span id="l924">            return false;</span><a href="#l924"></a>
<span id="l925">        }</span><a href="#l925"></a>
<span id="l926"></span><a href="#l926"></a>
<span id="l927">        if ((ops &amp; (Net.POLLERR | Net.POLLHUP)) != 0) {</span><a href="#l927"></a>
<span id="l928">            newOps = intOps;</span><a href="#l928"></a>
<span id="l929">            sk.nioReadyOps(newOps);</span><a href="#l929"></a>
<span id="l930">            // No need to poll again in checkConnect,</span><a href="#l930"></a>
<span id="l931">            // the error will be detected there</span><a href="#l931"></a>
<span id="l932">            readyToConnect = true;</span><a href="#l932"></a>
<span id="l933">            return (newOps &amp; ~oldOps) != 0;</span><a href="#l933"></a>
<span id="l934">        }</span><a href="#l934"></a>
<span id="l935"></span><a href="#l935"></a>
<span id="l936">        if (((ops &amp; Net.POLLIN) != 0) &amp;&amp;</span><a href="#l936"></a>
<span id="l937">            ((intOps &amp; SelectionKey.OP_READ) != 0) &amp;&amp;</span><a href="#l937"></a>
<span id="l938">            (state == ST_CONNECTED))</span><a href="#l938"></a>
<span id="l939">            newOps |= SelectionKey.OP_READ;</span><a href="#l939"></a>
<span id="l940"></span><a href="#l940"></a>
<span id="l941">        if (((ops &amp; Net.POLLCONN) != 0) &amp;&amp;</span><a href="#l941"></a>
<span id="l942">            ((intOps &amp; SelectionKey.OP_CONNECT) != 0) &amp;&amp;</span><a href="#l942"></a>
<span id="l943">            ((state == ST_UNCONNECTED) || (state == ST_PENDING))) {</span><a href="#l943"></a>
<span id="l944">            newOps |= SelectionKey.OP_CONNECT;</span><a href="#l944"></a>
<span id="l945">            readyToConnect = true;</span><a href="#l945"></a>
<span id="l946">        }</span><a href="#l946"></a>
<span id="l947"></span><a href="#l947"></a>
<span id="l948">        if (((ops &amp; Net.POLLOUT) != 0) &amp;&amp;</span><a href="#l948"></a>
<span id="l949">            ((intOps &amp; SelectionKey.OP_WRITE) != 0) &amp;&amp;</span><a href="#l949"></a>
<span id="l950">            (state == ST_CONNECTED))</span><a href="#l950"></a>
<span id="l951">            newOps |= SelectionKey.OP_WRITE;</span><a href="#l951"></a>
<span id="l952"></span><a href="#l952"></a>
<span id="l953">        sk.nioReadyOps(newOps);</span><a href="#l953"></a>
<span id="l954">        return (newOps &amp; ~oldOps) != 0;</span><a href="#l954"></a>
<span id="l955">    }</span><a href="#l955"></a>
<span id="l956"></span><a href="#l956"></a>
<span id="l957">    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l957"></a>
<span id="l958">        return translateReadyOps(ops, sk.nioReadyOps(), sk);</span><a href="#l958"></a>
<span id="l959">    }</span><a href="#l959"></a>
<span id="l960"></span><a href="#l960"></a>
<span id="l961">    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l961"></a>
<span id="l962">        return translateReadyOps(ops, 0, sk);</span><a href="#l962"></a>
<span id="l963">    }</span><a href="#l963"></a>
<span id="l964"></span><a href="#l964"></a>
<span id="l965">    // package-private</span><a href="#l965"></a>
<span id="l966">    int poll(int events, long timeout) throws IOException {</span><a href="#l966"></a>
<span id="l967">        assert Thread.holdsLock(blockingLock()) &amp;&amp; !isBlocking();</span><a href="#l967"></a>
<span id="l968"></span><a href="#l968"></a>
<span id="l969">        synchronized (readLock) {</span><a href="#l969"></a>
<span id="l970">            int n = 0;</span><a href="#l970"></a>
<span id="l971">            try {</span><a href="#l971"></a>
<span id="l972">                begin();</span><a href="#l972"></a>
<span id="l973">                synchronized (stateLock) {</span><a href="#l973"></a>
<span id="l974">                    if (!isOpen())</span><a href="#l974"></a>
<span id="l975">                        return 0;</span><a href="#l975"></a>
<span id="l976">                    readerThread = NativeThread.current();</span><a href="#l976"></a>
<span id="l977">                }</span><a href="#l977"></a>
<span id="l978">                n = Net.poll(fd, events, timeout);</span><a href="#l978"></a>
<span id="l979">            } finally {</span><a href="#l979"></a>
<span id="l980">                readerCleanup();</span><a href="#l980"></a>
<span id="l981">                end(n &gt; 0);</span><a href="#l981"></a>
<span id="l982">            }</span><a href="#l982"></a>
<span id="l983">            return n;</span><a href="#l983"></a>
<span id="l984">        }</span><a href="#l984"></a>
<span id="l985">    }</span><a href="#l985"></a>
<span id="l986"></span><a href="#l986"></a>
<span id="l987">    /**</span><a href="#l987"></a>
<span id="l988">     * Translates an interest operation set into a native poll event set</span><a href="#l988"></a>
<span id="l989">     */</span><a href="#l989"></a>
<span id="l990">    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {</span><a href="#l990"></a>
<span id="l991">        int newOps = 0;</span><a href="#l991"></a>
<span id="l992">        if ((ops &amp; SelectionKey.OP_READ) != 0)</span><a href="#l992"></a>
<span id="l993">            newOps |= Net.POLLIN;</span><a href="#l993"></a>
<span id="l994">        if ((ops &amp; SelectionKey.OP_WRITE) != 0)</span><a href="#l994"></a>
<span id="l995">            newOps |= Net.POLLOUT;</span><a href="#l995"></a>
<span id="l996">        if ((ops &amp; SelectionKey.OP_CONNECT) != 0)</span><a href="#l996"></a>
<span id="l997">            newOps |= Net.POLLCONN;</span><a href="#l997"></a>
<span id="l998">        sk.selector.putEventOps(sk, newOps);</span><a href="#l998"></a>
<span id="l999">    }</span><a href="#l999"></a>
<span id="l1000"></span><a href="#l1000"></a>
<span id="l1001">    public FileDescriptor getFD() {</span><a href="#l1001"></a>
<span id="l1002">        return fd;</span><a href="#l1002"></a>
<span id="l1003">    }</span><a href="#l1003"></a>
<span id="l1004"></span><a href="#l1004"></a>
<span id="l1005">    public int getFDVal() {</span><a href="#l1005"></a>
<span id="l1006">        return fdVal;</span><a href="#l1006"></a>
<span id="l1007">    }</span><a href="#l1007"></a>
<span id="l1008"></span><a href="#l1008"></a>
<span id="l1009">    @Override</span><a href="#l1009"></a>
<span id="l1010">    public String toString() {</span><a href="#l1010"></a>
<span id="l1011">        StringBuffer sb = new StringBuffer();</span><a href="#l1011"></a>
<span id="l1012">        sb.append(this.getClass().getSuperclass().getName());</span><a href="#l1012"></a>
<span id="l1013">        sb.append('[');</span><a href="#l1013"></a>
<span id="l1014">        if (!isOpen())</span><a href="#l1014"></a>
<span id="l1015">            sb.append(&quot;closed&quot;);</span><a href="#l1015"></a>
<span id="l1016">        else {</span><a href="#l1016"></a>
<span id="l1017">            synchronized (stateLock) {</span><a href="#l1017"></a>
<span id="l1018">                switch (state) {</span><a href="#l1018"></a>
<span id="l1019">                case ST_UNCONNECTED:</span><a href="#l1019"></a>
<span id="l1020">                    sb.append(&quot;unconnected&quot;);</span><a href="#l1020"></a>
<span id="l1021">                    break;</span><a href="#l1021"></a>
<span id="l1022">                case ST_PENDING:</span><a href="#l1022"></a>
<span id="l1023">                    sb.append(&quot;connection-pending&quot;);</span><a href="#l1023"></a>
<span id="l1024">                    break;</span><a href="#l1024"></a>
<span id="l1025">                case ST_CONNECTED:</span><a href="#l1025"></a>
<span id="l1026">                    sb.append(&quot;connected&quot;);</span><a href="#l1026"></a>
<span id="l1027">                    if (!isInputOpen)</span><a href="#l1027"></a>
<span id="l1028">                        sb.append(&quot; ishut&quot;);</span><a href="#l1028"></a>
<span id="l1029">                    if (!isOutputOpen)</span><a href="#l1029"></a>
<span id="l1030">                        sb.append(&quot; oshut&quot;);</span><a href="#l1030"></a>
<span id="l1031">                    break;</span><a href="#l1031"></a>
<span id="l1032">                }</span><a href="#l1032"></a>
<span id="l1033">                InetSocketAddress addr = localAddress();</span><a href="#l1033"></a>
<span id="l1034">                if (addr != null) {</span><a href="#l1034"></a>
<span id="l1035">                    sb.append(&quot; local=&quot;);</span><a href="#l1035"></a>
<span id="l1036">                    sb.append(Net.getRevealedLocalAddressAsString(addr));</span><a href="#l1036"></a>
<span id="l1037">                }</span><a href="#l1037"></a>
<span id="l1038">                if (remoteAddress() != null) {</span><a href="#l1038"></a>
<span id="l1039">                    sb.append(&quot; remote=&quot;);</span><a href="#l1039"></a>
<span id="l1040">                    sb.append(remoteAddress().toString());</span><a href="#l1040"></a>
<span id="l1041">                }</span><a href="#l1041"></a>
<span id="l1042">            }</span><a href="#l1042"></a>
<span id="l1043">        }</span><a href="#l1043"></a>
<span id="l1044">        sb.append(']');</span><a href="#l1044"></a>
<span id="l1045">        return sb.toString();</span><a href="#l1045"></a>
<span id="l1046">    }</span><a href="#l1046"></a>
<span id="l1047"></span><a href="#l1047"></a>
<span id="l1048"></span><a href="#l1048"></a>
<span id="l1049">    // -- Native methods --</span><a href="#l1049"></a>
<span id="l1050"></span><a href="#l1050"></a>
<span id="l1051">    private static native int checkConnect(FileDescriptor fd,</span><a href="#l1051"></a>
<span id="l1052">                                           boolean block, boolean ready)</span><a href="#l1052"></a>
<span id="l1053">        throws IOException;</span><a href="#l1053"></a>
<span id="l1054"></span><a href="#l1054"></a>
<span id="l1055">    private static native int sendOutOfBandData(FileDescriptor fd, byte data)</span><a href="#l1055"></a>
<span id="l1056">        throws IOException;</span><a href="#l1056"></a>
<span id="l1057"></span><a href="#l1057"></a>
<span id="l1058">    static {</span><a href="#l1058"></a>
<span id="l1059">        IOUtil.load();</span><a href="#l1059"></a>
<span id="l1060">        nd = new SocketDispatcher();</span><a href="#l1060"></a>
<span id="l1061">    }</span><a href="#l1061"></a>
<span id="l1062"></span><a href="#l1062"></a>
<span id="l1063">}</span><a href="#l1063"></a></pre>
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

