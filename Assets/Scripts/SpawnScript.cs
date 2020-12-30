using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnScript : MonoBehaviour
{
    public GameObject enemy, enemy2, enemy3;

    public float spawnRate = 0.1f;
    float nextSpawn = 0.0f;
    int spawnEnemy;

    Vector2 whereToSpawn;

    float randX;

    // Update is called once per frame
    void Update()
    {
        if (Time.time > nextSpawn)
        {
            spawnEnemy = Random.Range(1, 4);
            randX = Random.Range(-8.0f, 10.0f);
            whereToSpawn = new Vector2(randX, transform.position.y);

            switch (spawnEnemy)
            {
                case 1:
                    Instantiate (enemy, whereToSpawn, Quaternion.identity);
                    break;
                case 2:
                    Instantiate (enemy2, whereToSpawn, Quaternion.identity);
                    break;
                case 3:
                    Instantiate (enemy3, whereToSpawn, Quaternion.identity);
                    break;
            }

            nextSpawn = Time.time + spawnRate;
        }
    }
}
