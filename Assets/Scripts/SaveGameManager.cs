using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public class SaveGameManager : MonoBehaviour
{
    public static SaveGameManager instance;
    public static string persistentDataPath;
    //public Keybindings keys;
    // Start is called before the first frame update

    void Start()
	{
		Debug.Log(Application.persistentDataPath);
	}

    void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
    }

    public bool IsSaveFile()
    {
        return Directory.Exists(Application.persistentDataPath + "game_save");
    }

    public void SaveGame()
    {
        if (!IsSaveFile())
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save");
        }
    }
}