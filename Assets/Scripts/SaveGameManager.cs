using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using UnityEngine;

public class SaveGameManager : MonoBehaviour
{
    public static SaveGameManager instance;
    public static string persistentDataPath;
    public Character character;

    BinaryFormatter bf = new BinaryFormatter();

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
        if (!Directory.Exists(Application.persistentDataPath + "/game_save/character_status"))
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save/character_status");
        }

        FileStream file = File.Create(Application.persistentDataPath + "/game_save/character_status/save.txt");

        var json = JsonUtility.ToJson(character);
        bf.Serialize(file, json);
        file.Close();
    }

    public void LoadGame()
    {
        if (!Directory.Exists(Application.persistentDataPath + "/game_save/character_status"))
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save/character_status");
        }
        if (File.Exists(Application.persistentDataPath + "/game_save/character_status/save.txt"))
        {
            FileStream file = File.Open(Application.persistentDataPath + "/game_save/character_status/save.txt", FileMode.Open);
            JsonUtility.FromJsonOverwrite((string)bf.Deserialize(file), character);
            file.Close();
        }
    }
}