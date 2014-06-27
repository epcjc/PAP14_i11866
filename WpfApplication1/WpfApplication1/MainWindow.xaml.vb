Imports MySql.Data.MySqlClient
Imports System.Data
Imports WpfApplication1.inicio
Imports WpfApplication1.responder
Imports WpfApplication1.frequentes
Imports WpfApplication1.destaques
Imports WpfApplication1.config
Imports WpfApplication1.administradores


Class MainWindow

    Dim conn As New MySqlConnection("server=projetos.epcjc.net; user id=i11866; password=DretNed7; database=i11866")
    Dim cinicio As New inicio
    Dim cresponder As New responder
    Dim cfrequentes As New frequentes
    Dim cdestaques As New destaques
    Dim cconfig As New config
    Dim cadministradores As New administradores


    Private Sub sair_Click(sender As Object, e As RoutedEventArgs) Handles sair.Click
        Application.Current.Shutdown()
    End Sub

    Private Sub Grid_MouseLeftButtonDown(sender As Object, e As MouseButtonEventArgs)
        Me.DragMove()

    End Sub

    Private Sub minimize_Click(sender As Object, e As RoutedEventArgs) Handles minimize.Click
        Me.WindowState = WindowState.Minimized
    End Sub
    Private Sub inicio_Click(sender As Object, e As RoutedEventArgs) Handles inicio.Click
        Me.main.Content = cinicio
    End Sub


    Private Sub responder_Click(sender As Object, e As RoutedEventArgs) Handles responder.Click
        Me.main.Content = cresponder
    End Sub

    Private Sub perguntasfrequentes_Click(sender As Object, e As RoutedEventArgs) Handles perguntasfrequentes.Click
        Me.main.Content = cfrequentes
    End Sub


    Private Sub connfigura_Click(sender As Object, e As RoutedEventArgs) Handles connfigura.Click
        Dim webAddress As String = "http://projetos.epcjc.net/~i11866/backoffice/login.php"
        Process.Start(webAddress)
    End Sub

    Private Sub site_Click(sender As Object, e As RoutedEventArgs) Handles site.Click
        Dim webAddress As String = "http://projetos.epcjc.net/~i11866/"
        Process.Start(webAddress)
    End Sub

    Private Sub basedados_Click(sender As Object, e As RoutedEventArgs) Handles basedados.Click
        Dim webAddress As String = "http://projetos.epcjc.net/phpmyadmin/"
        Process.Start(webAddress)

    End Sub

    Private Sub ajuda1_Click(sender As Object, e As RoutedEventArgs) Handles ajuda1.Click

        Dim p As New Process()
        Dim psi As New ProcessStartInfo("C:\Users\Denis\Documents\Visual Studio 2012\Projects\WpfApplication1\WpfApplication1\help.chm")
        psi.Verb = "open"
        p.StartInfo = psi
        p.Start()

        Console.Read()
    End Sub



    Private Sub destaques_Click(sender As Object, e As RoutedEventArgs) Handles destaques.Click
        Me.main.Content = cdestaques
    End Sub

    Private Sub config_Click(sender As Object, e As RoutedEventArgs) Handles config.Click
        Me.main.Content = cconfig
    End Sub

    Private Sub administradores_Click(sender As Object, e As RoutedEventArgs) Handles administradores.Click
        Me.main.Content = cadministradores
    End Sub

    Private Sub sair1_Click(sender As Object, e As RoutedEventArgs) Handles sair1.Click
        Dim frm As login = New login
        frm.Show()

        Me.Close()
    End Sub

    Private Sub info_Click(sender As Object, e As RoutedEventArgs) Handles info.Click

    End Sub
End Class
