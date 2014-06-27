Imports MySql.Data.MySqlClient
Imports System.Data
Public Class administradores

    Private Sub UserControl_Loaded(sender As Object, e As RoutedEventArgs) Handles MyBase.Loaded
        IdTextBox.Visibility = System.Windows.Visibility.Hidden
        Dim I11866DataSet As WpfApplication1.i11866DataSet = CType(Me.FindResource("I11866DataSet"), WpfApplication1.i11866DataSet)
        'Load data into the table users. You can modify this code as needed.
        Dim I11866DataSetusersTableAdapter As WpfApplication1.i11866DataSetTableAdapters.usersTableAdapter = New WpfApplication1.i11866DataSetTableAdapters.usersTableAdapter()
        I11866DataSetusersTableAdapter.Fill(I11866DataSet.users)
        Dim UsersViewSource As System.Windows.Data.CollectionViewSource = CType(Me.FindResource("UsersViewSource"), System.Windows.Data.CollectionViewSource)
        UsersViewSource.View.MoveCurrentToFirst()
    End Sub


    Private Sub Button_Click_1(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "Delete FROM users WHERE id  =" + IdTextBox.Text

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Registo eliminado!")
        Else
            MessageBox.Show("Ocorreu um erro ao eliminar o registo. Verifique a ligação á internet.")
        End If
        con.Close()
    End Sub
    Function getSHA1Hash(ByVal strToHash As String) As String

        Dim sha1Obj As New Security.Cryptography.SHA1CryptoServiceProvider
        Dim bytesToHash() As Byte = System.Text.Encoding.ASCII.GetBytes(strToHash)

        bytesToHash = sha1Obj.ComputeHash(bytesToHash)

        Dim strResult As String = ""

        For Each b As Byte In bytesToHash
            strResult += b.ToString("x2")
        Next

        Return strResult
    End Function

    Private Sub Button_Click_2(sender As Object, e As RoutedEventArgs)
        Dim Query As String
        'Query = "INSERT INTO  userreg"
        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        'Dim sql As MySqlCommand = New MySqlCommand(Query, con)

        Query = "INSERT INTO users(username,password)VALUES('" + addusername.Text + "','" + getSHA1Hash(addpw.Text) + "'" + ")"



        con.Open()

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Nova Informação foi inserida")
        Else
            MessageBox.Show("Ocorreu um erro. Verifique a ligação á internet")
        End If
        addusername.Text = ""
        addpw.Text = ""

        con.Close()
    End Sub
End Class
