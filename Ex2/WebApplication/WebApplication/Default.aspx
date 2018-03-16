<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master"
    AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="WebApplication._Default" %>



<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>EX2</h2>
    <div style="border : 1px solid red ; width:300px">
        <p>Họ và tên : Nguyễn Văn Hoan</p>
        <p>MSSV : 1411275</p>
    </div>
    <form method="POST" id="f1">
        Latitude 1:
        <input id="lat1" type="number" />
        <br />
        Longitude 1:
        <input id="long1" type="number" />
        <br />
        Latitude 2:
        <input id="lat2" type="number" />
        <br />
        Longitude 2:
        <input id="long2" type="number" />
        <br />
        <button id="tong" onclick="abc()">Khoang cach</button>

    </form>
    <script>
    $("#f1").submit(function() {
        doSomething(); 
    });
    </script>
    <br />
    <script src="Scripts/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">

       
            function abc() {
                var a = $("#lat1").val();
                var b = $("#long1").val();
                var c = $("#lat2").val();
                var d = $("#long2").val();

                var url = "api/Tong" + "?Lat1=" + a + "&Long1="+b+"&Lat2="+c + "&Long2="+d;
                $.getJSON(url,
                    function (data) {
                        alert("Khỏang cách 2 điểm là : " + data + " km");
                    });
            }

    </script>
</asp:Content>
