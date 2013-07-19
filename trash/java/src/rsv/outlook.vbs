Private Sub Application_NewMail()

    Set myOlApp = Outlook.Application

    Dim myExplorers As Outlook.Explorers
    Dim myFolder As Outlook.Folder
    Dim x, y As Integer

    Set myExplorers = myOlApp.Explorers

    Set myFolder = myOlApp.GetNamespace("MAPI").GetDefaultFolder(olFolderInbox)

    Dim lt As MailItem

    Set lt = myFolder.Items.GetLast

    Dim filesize As Integer
    Dim FlName As String


    FlName = "C:\" & lt.EntryID & ".txt"

    filesize = FreeFile()

    Open FlName For Output As #filesize

    Print #filesize, lt.SenderName
    Print #filesize, lt.SenderEmailAddress
    Print #filesize, lt.Subject
    Print #filesize, lt.Body

    Close #filesize

End Sub