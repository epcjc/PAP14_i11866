Imports MySql.Data.MySqlClient
Imports System.Data
Public Class config

    Private Sub UserControl_Loaded(sender As Object, e As RoutedEventArgs) Handles MyBase.Loaded
        IdTextBox.Visibility = System.Windows.Visibility.Hidden
        Dim I11866DataSet As WpfApplication1.i11866DataSet = CType(Me.FindResource("I11866DataSet"), WpfApplication1.i11866DataSet)
        'Load data into the table config. You can modify this code as needed.
        Dim I11866DataSetconfigTableAdapter As WpfApplication1.i11866DataSetTableAdapters.configTableAdapter = New WpfApplication1.i11866DataSetTableAdapters.configTableAdapter()
        I11866DataSetconfigTableAdapter.Fill(I11866DataSet.config)
        Dim ConfigViewSource As System.Windows.Data.CollectionViewSource = CType(Me.FindResource("ConfigViewSource"), System.Windows.Data.CollectionViewSource)
        ConfigViewSource.View.MoveCurrentToFirst()
    End Sub

    Private Sub Button_Click(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "UPDATE config SET nome ='" + NomeTextBox.Text + "',"
        Query = Query + "mensagem = '" + MensagemTextBox.Text + "',"
        Query = Query + "localizacao = '" + LocalizacaoTextBox.Text + "',"
        Query = Query + "contactos = '" + ContactosTextBox.Text + "',"
        Query = Query + "facebook = '" + FacebookTextBox.Text + "',"
        Query = Query + "youtube = '" + YoutubeTextBox.Text + "',"
        Query = Query + "linkedin = '" + LinkedinTextBox.Text + "',"
        Query = Query + "twitter = '" + TwitterTextBox.Text + "',"
        Query = Query + "google = '" + GoogleTextBox.Text + "',"
        Query = Query + "pinterest = '" + PinterestTextBox.Text + "',"
        Query = Query + "footer = '" + FooterTextBox.Text + "'"
        Query = Query + " WHERE id = " + IdTextBox.Text

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Dados atualizados!")
        Else
            MessageBox.Show("Ocorreu um erro. Verifique a ligação á internet.")
        End If
        con.Close()
    End Sub
End Class
